package com.myhan.autocodereview.model;

import lombok.Data;

@Data
public class Config {
    private User user;
    private Organization organization;

    @Data
    public static class User {
        private String oauth;
    }

    @Data
    public static class Organization {
        private String name;
    }
}
