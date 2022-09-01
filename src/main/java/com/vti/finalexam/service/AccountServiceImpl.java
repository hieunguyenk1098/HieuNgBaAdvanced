package com.vti.finalexam.service;

import com.vti.finalexam.Constants.ACCOUNT;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.dto.*;
import com.vti.finalexam.entity.dto.AccountResponseDTO.DepartmentInnerDTO;
import com.vti.finalexam.entity.dto.request.LoginRequest;
import com.vti.finalexam.entity.dto.response.JwtResponse;
import com.vti.finalexam.exception.BusinessErrorException;
import com.vti.finalexam.exception.BusinessError;
import com.vti.finalexam.exception.NotFoundException;
import com.vti.finalexam.repository.AccountRepository;
import com.vti.finalexam.security.JwtTokenProvider;
import com.vti.finalexam.service.criteria.AccountCriteria;
import com.vti.finalexam.service.specification.AccountSpecification;
import com.vti.finalexam.service.specification.Expression;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private final AccountRepository accountRepository;

    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private final QueryService<Account> queryService;

    @Autowired
    private final DepartmentService departmentService;

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    public AccountServiceImpl(AccountRepository accountRepository,
        ModelMapper modelMapper,
        QueryService<Account> queryService,
        DepartmentService departmentService) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
        this.queryService = queryService;
        this.departmentService = departmentService;
    }

    @Override
    public List<AccountResponseDTO> findAll() {
//        List<Account> accountList = accountRepository.findAll();
//        List<AccountResponseDTO> accountResponseDTOList = new ArrayList<>();
//        for (Account account : accountList) {
//            AccountResponseDTO accResDTO = toDTO(account);
//            accountResponseDTOList.add(accResDTO);
//        }
//        return accountResponseDTOList;

        return accountRepository
            .findAll()
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public Page<AccountDTO> findAllByCriteria(AccountCriteria accountCriteria, Pageable pageable) {

        Specification<Account> spec = buildSpecification(accountCriteria);

        Page<Account> page = accountRepository.findAll(spec, pageable);

        List<AccountDTO> list = page.getContent().stream()
            .map(account -> modelMapper.map(account, AccountDTO.class)).collect(
                Collectors.toList());

        return new PageImpl<>(list, pageable, page.getTotalElements());
    }

    private AccountResponseDTO toDTO(Account account) {
        AccountResponseDTO accResTO = new AccountResponseDTO();

        accResTO.setId(account.getId());
        accResTO.setUsername(account.getUsername());
        accResTO.setFirstName(account.getFirstName());
        accResTO.setLastName(account.getLastName());
        accResTO.setRole(account.getRole());
        accResTO.setDepartmentInnerDTO(
            new DepartmentInnerDTO(
                account.getDepartment().getId(),
                account.getDepartment().getName(),
                account.getDepartment().getTotalMember(),
                account.getDepartment().getType(),
                account.getDepartment().getCreatedDate()
            ));
        return accResTO;
    }

    @Override
    public Optional<Account> findOne(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    @Transactional
    public Account create(Account account) {
        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public Optional<AccountDTO> create(AccountFormDTO accountFormDTO) {
        return departmentService.findOne(accountFormDTO.getDepartmentId()).map(department -> {
            Account account = modelMapper.map(accountFormDTO, Account.class);
            account.department(department);
            create(account.id(null));
            return modelMapper.map(account, AccountDTO.class);
        });
    }

    @Override
    @Transactional
    public Account update(Long id, Account account) throws NotFoundException {
        return findOne(id).map(acc -> {
            account.setId(id);
            return accountRepository.save(account);
        }).orElseThrow(
            () -> new BusinessErrorException()
                .businessError(
                    new BusinessError()
                        .errorCode("error.account.idIsNotExists")
                        .params(Collections.singletonList(id))));
    }

    @Override
    @Transactional
    public Optional<AccountDTO> update(Long id, AccountFormDTO accountFormDTO)
        throws NotFoundException {
        Account account = modelMapper.map(accountFormDTO, Account.class);
        update(id, account);
        return Optional.ofNullable(modelMapper.map(account, AccountDTO.class));
    }

    @Override
    @Transactional
    public AccountDTO delete(Long id) throws NotFoundException {
        return findOne(id).map(acc -> {
            accountRepository.delete(acc);
            return modelMapper.map(acc, AccountDTO.class);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public List<Account> findByUsername(String username) {
        return accountRepository.findByUsernameLike("%" + username + "%");
    }

    @Override
    public List<AccountResponseDTO> findByUsernameToDTO(String username) {
        return findByUsername(username)
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<AccountResponseDTO> findByFirstNameToDTO(String firstName) {
        //HQL
//        return accountRepository.findByFirstNameHQL("%" + firstName + "%")
//            .stream()
//            .map(this::toDTO)
//            .collect(Collectors.toList());

        //SQL
        return accountRepository.findByFirstNameSQL("%" + firstName + "%")
            .stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AccountResponseDTO delete(String username) throws NotFoundException {
        return findByUsernameEquals(username).map(acc -> {
            accountRepository.deleteByUsername(username);
            return toDTO(acc);
        }).orElseThrow(NotFoundException::new);
    }

    @Override
    public Optional<Account> findByUsernameEquals(String username) {
        return accountRepository.findByUsernameEquals(username);
    }

    @Override
    public Page<AccountResponseDTO> findAllWithPage(Pageable pageable) {
//        Page<Account> pageAccount = accountRepository.findAll(pageable);
//
//        List<AccountResponseDTO> accountResponseDTOList = new ArrayList<>();
//        pageAccount.getContent().forEach(account -> {
//            accountResponseDTOList.add(toDTO(account));
//        });
//
//        return new PageImpl<>(accountResponseDTOList, pageable, pageAccount.getTotalElements());

        Page<Account> pageAccount = accountRepository.findAll(pageable);
        return new PageImpl<>(
            pageAccount
                .getContent()
                .stream().map(this::toDTO)
                .collect(Collectors.toList()),
            pageable,
            pageAccount.getTotalElements());
    }

    @Override
    public Page<Account> findAll(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }

    @Override
    public Page<AccountDTO> findAllToDTO(Pageable pageable) {
        Page<Account> page = findAll(pageable);
        List<Account> list = page.getContent();
        return new PageImpl<>(
            modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
            }.getType()),
            pageable,
            page.getTotalElements()
        );
    }

    @Override
    public Optional<AccountDTO> findOneToDTO(Long id) {
//        Optional<Account> accountOptional = findOne(id);
//        if (accountOptional.get() == null) {
//            return Optional.empty();
//        } else {
//            AccountDTO accountDTO = modelMapper.map(accountOptional.get(), AccountDTO.class);
//            return Optional.of(accountDTO);
//        }

        return findOne(id).map(account -> modelMapper.map(account, AccountDTO.class));
    }

    @Override
    public List<AccountDTO> search(Expression expression) {
        Specification<Account> spec = buildWhere(expression);
        List<Account> list = accountRepository.findAll(spec);
        return modelMapper.map(list, new TypeToken<List<AccountDTO>>() {
        }.getType());
    }

    Specification<Account> buildWhere(Expression expression) {
        AccountSpecification accountSpecification = new AccountSpecification(expression);
        return Specification.where(accountSpecification);
    }

    private Specification<Account> buildSpecification(AccountCriteria accountCriteria) {

        Specification<Account> specification = Specification.where(null);

        if (accountCriteria.getId() != null) {
            specification = specification
                .and(queryService.buildLongFilter(ACCOUNT.ID, accountCriteria.getId()));
        }
        if (accountCriteria.getUsername() != null) {
            specification = specification.and(
                queryService.buildStringFilter(ACCOUNT.USERNAME, accountCriteria.getUsername()));
        }
        if (accountCriteria.getFirstName() != null) {
            specification = specification.and(
                queryService.buildStringFilter(ACCOUNT.FIRST_NAME, accountCriteria.getFirstName()));
        }
        if (accountCriteria.getLastName() != null) {
            specification = specification.and(
                queryService.buildStringFilter(ACCOUNT.LAST_NAME, accountCriteria.getLastName()));
        }
        if (accountCriteria.getRole() != null) {
            specification = specification
                .and(queryService.buildStringFilter(ACCOUNT.ROLE, accountCriteria.getRole()));
        }
        if (accountCriteria.getUsernameOrFirstNameOrLastName() != null) {
            Specification<Account> orSpec = Specification.where(null);

            orSpec = orSpec.or(queryService.buildStringFilter(ACCOUNT.USERNAME,
                accountCriteria.getUsernameOrFirstNameOrLastName()))
                .or(queryService.buildStringFilter(ACCOUNT.FIRST_NAME,
                    accountCriteria.getUsernameOrFirstNameOrLastName()))
                .or(queryService.buildStringFilter(ACCOUNT.LAST_NAME,
                    accountCriteria.getUsernameOrFirstNameOrLastName()));
            specification = specification.and(orSpec);
        }

        return specification;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findAccountByUsername(username)
            .map(account -> new User(
                account.getUsername(),
                account.getPassword(),
                AuthorityUtils.createAuthorityList(account.getRole().toString())))
            .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public Optional<Account> findAccountByUsername(String username) {
        return accountRepository.findByUsernameEquals(username);
    }

    @Override
    public Optional<LoginResponse> findByUsernameInLogin(String username) {
        return findAccountByUsername(username).map(account -> modelMapper.map(account, LoginResponse.class));
    }

    @Override
    public AccountDTO registerAccount(RegisterAccount registerAccount) {
        validateRegisterAccount(registerAccount);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Account account = new Account()
            .username(registerAccount.getUsername())
            .password(bCryptPasswordEncoder.encode(registerAccount.getPassword()));

        return modelMapper.map(accountRepository.save(account), AccountDTO.class);
    }

    @Override
    public JwtResponse login(LoginForm loginForm) {
        try {
            AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword())
            );
            String token = jwtTokenProvider.createToken(authenticate.getName(), authenticate.getAuthorities());
            return new JwtResponse(token);
        } catch (Exception e) {
            return new JwtResponse();
        }
    }

    @Override
    public JwtResponse login(LoginEmail loginEmail) {
        try {
            AuthenticationManager authenticationManager = authenticationConfiguration.getAuthenticationManager();
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginEmail.getEmail(), loginEmail.getPassword())
            );
            String token = jwtTokenProvider.createToken(authenticate.getName(), authenticate.getAuthorities());
            return new JwtResponse(token);
        } catch (Exception e) {
            return new JwtResponse();
        }
    }

    private void validateRegisterAccount(RegisterAccount registerAccount) {
        if (registerAccount.getUsername() == null || registerAccount.getUsername().equals("")) {
            throw new BusinessErrorException()
                .businessError(
                    new BusinessError()
                        .errorCode("error.registerAccount.usernameIsNotValid")
                        .params(Collections.singletonList(registerAccount.getUsername())));
        }

        if (registerAccount.getPassword() == null || registerAccount.getPassword().equals("")) {
            throw new BusinessErrorException()
                .businessError(
                    new BusinessError()
                        .errorCode("error.registerAccount.passwordIsNotValid")
                        .params(Collections.singletonList(registerAccount.getUsername())));
        }

        findByUsernameEquals(registerAccount.getUsername()).map(account -> {
            throw new BusinessErrorException()
                .businessError(
                    new BusinessError()
                        .errorCode("error.registerAccount.userIsExisted")
                        .params(Collections.singletonList(registerAccount.getUsername())));
        });
    }


}
