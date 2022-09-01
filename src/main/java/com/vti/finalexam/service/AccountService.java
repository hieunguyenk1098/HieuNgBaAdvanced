package com.vti.finalexam.service;

import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.dto.*;
import com.vti.finalexam.entity.dto.response.JwtResponse;
import com.vti.finalexam.exception.NotFoundException;
import com.vti.finalexam.service.criteria.AccountCriteria;
import com.vti.finalexam.service.specification.Expression;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService extends UserDetailsService {

    List<AccountResponseDTO> findAll();

    Page<AccountDTO> findAllByCriteria(AccountCriteria accountCriteria, Pageable pageable);

    Page<AccountResponseDTO> findAllWithPage(Pageable pageable);

    Optional<Account> findOne(Long id);

    Account create(Account account);

    Optional<AccountDTO> create(AccountFormDTO accountFormDTO);

    Account update(Long id, Account account) throws NotFoundException;

    Optional<AccountDTO> update(Long id, AccountFormDTO accountFormDTO) throws NotFoundException;

    AccountDTO delete(Long id) throws NotFoundException;

    List<Account> findByUsername(String username);

    List<AccountResponseDTO> findByUsernameToDTO(String username);

    List<AccountResponseDTO> findByFirstNameToDTO(String firstName);

    AccountResponseDTO delete(String username) throws NotFoundException;

    Optional<Account> findByUsernameEquals(String username);

    Page<Account> findAll(Pageable pageable);

    Page<AccountDTO> findAllToDTO(Pageable pageable);

    Optional<AccountDTO> findOneToDTO(Long id);

    List<AccountDTO> search(Expression expression);

    Optional<Account> findAccountByUsername(String username);

    Optional<LoginResponse> findByUsernameInLogin(String username);

    AccountDTO registerAccount(RegisterAccount registerAccount);

    JwtResponse login(LoginForm loginForm);

    JwtResponse login(LoginEmail loginEmail);
}
