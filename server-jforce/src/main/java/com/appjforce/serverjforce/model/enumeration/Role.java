package com.appjforce.serverjforce.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    USER ("USER"),
    ADMIN ("ADMIN");

    private final String role;
}
