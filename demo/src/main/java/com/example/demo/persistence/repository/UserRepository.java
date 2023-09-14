package com.example.demo.persistence.repository;

import com.example.demo.persistence.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Page<UserEntity> findAll(Pageable pageable);
}
