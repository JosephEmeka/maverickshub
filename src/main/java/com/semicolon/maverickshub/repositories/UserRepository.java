package com.semicolon.maverickshub.repositories;

import com.semicolon.maverickshub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//   @Query("SELECT s FROM User u WHERE u.email=:email")
    Optional<User> findByEmail(String email);
}
