package com.clean.infra.user;

import com.clean.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface UserJpaRepository extends JpaRepository<User, Long> {
}
