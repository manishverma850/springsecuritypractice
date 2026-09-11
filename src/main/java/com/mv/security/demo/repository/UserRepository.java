package com.mv.security.demo.repository;

import com.mv.security.demo.entity.userentity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findByUsername(String name);
}
