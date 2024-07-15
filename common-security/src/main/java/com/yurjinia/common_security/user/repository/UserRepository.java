package com.yurjinia.common_security.user.repository;


import com.yurjinia.common_security.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findAllByEmailIn(List<String> emails);
    boolean existsByEmail(String email);
}
