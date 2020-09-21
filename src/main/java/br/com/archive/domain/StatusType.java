package br.com.archive.domain;

import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusType {

    ACTIVE("Active"),
    INACTIVE("Inactive");

    private String value;

    StatusType(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
