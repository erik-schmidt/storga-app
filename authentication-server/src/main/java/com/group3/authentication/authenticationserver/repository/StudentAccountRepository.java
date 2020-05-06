package com.group3.authentication.authenticationserver.repository;

import com.group3.authentication.authenticationserver.model.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAccountRepository extends JpaRepository<StudentAccount, String> {
    StudentAccount findAllByUsername(String username);
}
