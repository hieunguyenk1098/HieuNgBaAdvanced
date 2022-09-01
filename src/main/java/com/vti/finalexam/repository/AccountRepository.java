package com.vti.finalexam.repository;

import com.vti.finalexam.entity.Account;
import java.util.List;
import java.util.Optional;

import com.vti.finalexam.entity.dto.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long>,
    JpaSpecificationExecutor<Account> {

    @Query("SELECT (count(a) = 0) from AccountEntity a where a.email = :email")
    boolean isEmailNotExists(String email);


    AccountEntity findByEmail(String email);

    List<Account> findByUsernameLike(String username);

    Optional<Account> findByUsernameEquals(String username);

    @Query("select a from Account a where a.firstName like :firstName")
    List<Account> findByFirstNameHQL(String firstName);

    @Query(value = "select * from account where first_name like :firstName", nativeQuery = true)
    List<Account> findByFirstNameSQL(String firstName);

    @Modifying
    @Query("delete from Account a where a.username = :username")
    void deleteByUsername(String username);
}
