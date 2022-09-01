package com.vti.finalexam.controller;

import com.vti.finalexam.Constants.API;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.dto.*;
import com.vti.finalexam.entity.dto.response.JwtResponse;
import com.vti.finalexam.exception.NotFoundException;
import com.vti.finalexam.service.AccountService;
import com.vti.finalexam.service.criteria.AccountCriteria;
import com.vti.finalexam.service.specification.Expression;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javax.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(API.BASE_API_V1 + API.ACCOUNTS)
@Validated
@Log4j2
@CrossOrigin("http://localhost:63342")
public class AccountController {

    @Autowired
    private final AccountService accountService;

    @Autowired
    private final MessageSource messageSource;

    // spring nó có 1 nơi (DI container)
    // chứa các khởi tạo các class mà được đánh dấu là : @service, @repository, @component,....
    // new AccountServiceImpl(), new DepartmentServiceImpl(),...
    // module nào dùng thì chỉ cần inject vào là xong
    // - qua contructor, thuộc tính, setter()

    @Autowired
    public AccountController(AccountService accountService,
        MessageSource messageSource) {
        this.accountService = accountService;
        this.messageSource = messageSource;
    }

    @GetMapping()
    ResponseEntity<Page<AccountDTO>> findAll(AccountCriteria accountCriteria, Pageable pageable) {
        log.info("Call api " + API.BASE_API_V1 + " to get all accounts");
        return ResponseEntity.ok()
            .body(accountService.findAllByCriteria(accountCriteria, pageable));
    }

    @GetMapping("/username/{value}")
    ResponseEntity<List<AccountResponseDTO>> findByUsername(
        @PathVariable(name = "value") String username) {
        return ResponseEntity.ok().body(accountService.findByUsernameToDTO(username));
    }

    @GetMapping("/firstName")
    ResponseEntity<List<AccountResponseDTO>> findByFirstName(
        @RequestParam String firstName) {
        return ResponseEntity.ok().body(accountService.findByFirstNameToDTO(firstName));
    }

    @DeleteMapping("/username/{username}")
    ResponseEntity<AccountResponseDTO> deleteByUsername(
        @PathVariable String username) {
        try {
            return ResponseEntity.ok().body(accountService.delete(username));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<AccountDTO>> findOneToDTO(@PathVariable Long id) {
        return ResponseEntity.ok().body(accountService.findOneToDTO(id));
    }

    @GetMapping("/search")
    ResponseEntity<List<AccountDTO>> search(
        @RequestParam String field,
        @RequestParam String operator,
        @RequestParam String value) {
        return ResponseEntity.ok()
            .body(accountService.search(new Expression(field, operator, value)));
    }

    @PostMapping()
    ResponseEntity<Optional<AccountDTO>> createAccount(
        @RequestBody @Valid AccountFormDTO accountFormDTO) {
        return ResponseEntity.ok().body(accountService.create(accountFormDTO));
    }

    @PutMapping("/{id}")
    ResponseEntity<Optional<AccountDTO>> updateAccount(@PathVariable Long id,
        @RequestBody @Valid AccountFormDTO accountFormDTO)
        throws NotFoundException {
        accountService.update(id, accountFormDTO);
        return ResponseEntity.ok().body(accountService.findOneToDTO(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<AccountDTO> deleteAccount(
        @PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(accountService.delete(id));
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse login(@Valid @RequestBody LoginForm loginForm) {
        return accountService.login(loginForm);
    }

    @PostMapping("/loginemail")
    @ResponseStatus(HttpStatus.OK)
    public JwtResponse login(@Valid @RequestBody LoginEmail loginEmail) {
        return accountService.login(loginEmail);
    }

}
