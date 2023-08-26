package com.spottag.enums;

public enum AccountRoleEnums {
    USER("USER"),
    ADMIN("ADMIN");

    private String value;

    AccountRoleEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
