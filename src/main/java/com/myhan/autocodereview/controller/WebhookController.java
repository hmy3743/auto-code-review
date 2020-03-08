package com.myhan.autocodereview.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/webhook")
@ResponseStatus(HttpStatus.OK)
public class WebhookController {
    @RequestMapping("github")
    public void github() {
        System.out.println("GITHUB");
    }
}
