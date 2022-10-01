package com.appjforce.serverjforce.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PostStatus {
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    NEW("NEW");

    private final String postStatus;
}
