package com.example.controller;

import com.example.service.BaiduService;
import com.example.service.CSDNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class CsdnController {

    @Autowired
    CSDNService csdnDriver;

    @PostMapping("/csdn")
    public HashMap<String, String> getByBaidu(String url) throws InterruptedException {
        HashMap<String, String> result = csdnDriver.getResult(url);

        return result;
    }
}
