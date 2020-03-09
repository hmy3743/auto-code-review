package com.myhan.autocodereview.model;

import lombok.Data;

@Data
public class PullRequestReview {
    private String body;
    private String event;
}
