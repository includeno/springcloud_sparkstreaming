package com.example.controller;

import com.example.service.BaiduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BaiduController {


    @Autowired
    BaiduService baiduDriver;


    @GetMapping("/baidu")
    public ArrayList<String> getByBaidu(String keyword,Integer page) throws InterruptedException {

        return baiduDriver.searchKeyword(keyword,page);
    }
}
