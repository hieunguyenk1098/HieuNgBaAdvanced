package com.vti.finalexam.controller;

import com.vti.finalexam.Constants.API;
import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.dto.AccountDTO;
import com.vti.finalexam.entity.dto.LoginForm;
import com.vti.finalexam.entity.dto.LoginResponse;
import com.vti.finalexam.entity.dto.RegisterAccount;
import com.vti.finalexam.service.AccountService;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API.BASE_API_V1)
@Validated
@Log4j2
@CrossOrigin("http://localhost:63342")
public class LoginController {

    private final AccountService accountService;
    private final AuthenticationManager authenticationManager;

    public LoginController(AccountService accountService,
        AuthenticationManager authenticationManager) {
        this.accountService = accountService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth/register")
    ResponseEntity<AccountDTO> registerAccount(@RequestBody RegisterAccount registerAccount) {
        return ResponseEntity.ok().body(accountService.registerAccount(registerAccount));
    }

    @PostMapping("/auth/login")
    @PreAuthorize("hasAnyAuthority()")
    ResponseEntity<Optional<LoginResponse>> login(@RequestBody LoginForm loginForm) {
        authenticate(loginForm);
        Optional<Account> accountDTO = accountService
            .findAccountByUsername(loginForm.getUsername());
        Optional<LoginResponse> loginResponse = accountService
            .findByUsernameInLogin(loginForm.getUsername());
        loginResponse = loginResponse
            .map(loginResponse1 -> loginResponse1.password(loginForm.getPassword()));

        return ResponseEntity.ok().body(loginResponse);
    }
    // hasAnyAuthority, hasAnyRole --> 1 list, chuỗi có 1 trong các quyền đó là có quyền truy cập api này
    // phải có tiền tố Role ở trước
    // hasAuthority, permission --> 1 chuỗi quyền duy nhất truy cập vào đc

    private void authenticate(LoginForm loginForm) {
        // TODO
        // Validate login form info
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginForm.getUsername(),
                loginForm.getPassword()));
    }
}
