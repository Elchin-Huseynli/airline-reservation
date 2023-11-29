package com.airline.user_ms.repository;

import com.airline.user_ms.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);
    Optional<User> findByUsernameIgnoreCaseAndIsEnable(String username, boolean isEnable);

}