package com.dinusha.soft.smonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SMonitorIndexPage {

    @GetMapping("/s-monitor")
    public String SMonitorHomePage() {
        return "./s-monitor/index.html";
    }
}
