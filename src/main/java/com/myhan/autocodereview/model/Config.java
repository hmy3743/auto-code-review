package com.myhan.autocodereview.model;

import lombok.Data;

@Data
public class Config {
    private User user;

    @Data
    public static class User {
        private String oauth;
    }
}
