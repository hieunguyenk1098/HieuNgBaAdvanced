package com.vti.finalexam.repository;

import com.vti.finalexam.entity.OtpAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpAccountRepository extends JpaRepository<OtpAccount, Long> {

    OtpAccount findByEmailAndOtp(String email, String otp);

}
