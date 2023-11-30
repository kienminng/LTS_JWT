package com.example.java_train.Entities.Enum;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    EMPLOYEE_READ("admin:read"),
    EMPLOYEE_UPDATE("admin:update"),
    EMPLOYEE_CREATE("admin:create"),
    EMPLOYEE_DELETE("admin:delete");

    @Getter
    private final String permission;
}
