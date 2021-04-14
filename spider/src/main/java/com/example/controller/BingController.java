package com.example.controller;


import com.example.service.BingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BingController {

    @Autowired
    BingService bingDriver;

    @GetMapping("/bing")
    public ArrayList<String> getByBing(String keyword, Integer page) throws InterruptedException {

        return bingDriver.searchKeyword(keyword,page);
    }
}