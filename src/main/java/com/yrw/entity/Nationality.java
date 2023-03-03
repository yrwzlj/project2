package com.yrw.entity;

import lombok.Data;

@Data
public class Nationality {
    private String uuid;

    private String name;

    private String code;

    private String flag;

    @Override
    public String toString() {
        return name;
    }
}
