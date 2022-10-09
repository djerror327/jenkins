package com.dinusha.soft.clusterresourcemonitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClusterResourceMonitorIndexPage {

    @GetMapping("/cluster-resource-monitor")
    public String clusterResourceMonitorHomePage() {
        return "./cluster-resource-monitor/index.html";
    }
}
