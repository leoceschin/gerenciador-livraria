package com.ceschin.library.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceschin.library.model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, UUID>{
    User findByUsername(String username);
}
