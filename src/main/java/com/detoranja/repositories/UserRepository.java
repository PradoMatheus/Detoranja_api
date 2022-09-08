package com.detoranja.repositories;

import com.detoranja.models.UserModel;
import org.hibernate.annotations.Cache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    boolean existsByUsername(String username);
    Optional<UserModel> findByUsername(String username);
}
