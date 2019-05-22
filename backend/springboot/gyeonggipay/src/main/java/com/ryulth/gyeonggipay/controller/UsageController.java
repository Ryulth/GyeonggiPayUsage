package com.ryulth.gyeonggipay.controller;

import com.ryulth.gyeonggipay.service.UsageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class UsageController {
    private UsageService usageService;

    @Autowired
    public UsageController(UsageService usageService){
        this.usageService = usageService;
    }
    @GetMapping("/usages")
    public String getUsages() throws IOException {
        return usageService.findAll();
    }
    @GetMapping("/usage")
    public String getUsage(@RequestParam(value = "address")String address) throws IOException {
        return usageService.findUsage(address);
    }
    @GetMapping("/usage/search")
    public String searchUsage(@RequestParam(value = "address")String address) throws IOException {
        return usageService.searchByAddress(address);
    }
}
