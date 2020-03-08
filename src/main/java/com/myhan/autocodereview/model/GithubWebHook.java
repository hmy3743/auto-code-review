package com.myhan.autocodereview.model;

import lombok.Data;

@Data
public class GithubWebHook {
    private PullRequest pull_request;
    private String action;
    private Integer number;

    @Data
    public static class PullRequest {
        private String url;
        private Head head;
        private Integer id;

        @Data
        public static class Head {
            private Repo repo;

            @Data
            public static class Repo {
                private Owner owner;
                private String name;
                private Integer id;

                @Data
                public static class Owner {
                    private String login;
                }
            }
        }
    }
}