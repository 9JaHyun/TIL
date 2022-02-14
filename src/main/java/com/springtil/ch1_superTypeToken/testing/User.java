package com.springtil.ch1_superTypeToken.testing;

import lombok.Data;
import lombok.Getter;

@Data
public class User {
    String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
