package com.myhan.autocodereview;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HookRegiser {
    @EventListener(ApplicationReadyEvent.class)
    public void regist() {

    }
}
