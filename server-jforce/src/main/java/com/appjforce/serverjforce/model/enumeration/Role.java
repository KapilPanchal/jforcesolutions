package com.appjforce.serverjforce.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ROLE_USER ("ROLE_USER"),
    ROLE_ADMIN ("ROLE_ADMIN");

    private final String role;
}
