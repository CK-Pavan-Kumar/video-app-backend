package com.videoappbackend.Repository;

import com.videoappbackend.Entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserData, Integer> {

    Optional<UserData> findByEmail(String email);
}
