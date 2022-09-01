package com.vti.finalexam.security;

import com.vti.finalexam.entity.Account;
import com.vti.finalexam.entity.dto.AccountEntity;
import com.vti.finalexam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = accountRepository.findByEmail(username);
        if (account == null){
            throw new UsernameNotFoundException("account not exist");
        }
        return User.builder()
                .roles(account.getRole())
                .username(account.getEmail())
                .password(account.getPassword())
                .build();
    }


    @Bean
    @Primary
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
