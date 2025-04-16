package com.clean.domain.user;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Component
public interface UserRepository {
    Optional<User> findById(Long userId);
}
