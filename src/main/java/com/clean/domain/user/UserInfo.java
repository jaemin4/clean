package com.clean.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class UserInfo {
    private final long userId;
    private final String username;
}
