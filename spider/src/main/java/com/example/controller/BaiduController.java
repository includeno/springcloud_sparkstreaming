package com.example.controller;

import com.example.service.BaiduLoadPageService;
import com.example.service.BaiduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class BaiduController {


    @Autowired
    BaiduService baiduDriver;

    @Autowired
    BaiduLoadPageService baiduLoadPageService;


    @GetMapping("/baidu")
    public ArrayList<String> getByBaidu(String keyword,Integer page) throws InterruptedException {
        ArrayList<String> result = baiduDriver.searchKeyword(keyword, page);

        return result;
    }

    @GetMapping("/baidupage")
    public ArrayList<String> getByBaiduPage(String keyword,Integer page) throws InterruptedException {
        ArrayList<String> result = baiduLoadPageService.searchKeyword(keyword, page);

        return result;
    }
}
