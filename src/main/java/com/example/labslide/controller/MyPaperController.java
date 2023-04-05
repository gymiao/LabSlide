package com.example.labslide.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.labslide.domain.Mypaper;
import com.example.labslide.service.MyPaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/mypaper")

public class MyPaperController {
//    @GetMapping
//    public String getString() {
//        return "Hello World!";
//    }
    @Autowired
    private MyPaperService paperService;
//

    @CrossOrigin
    @GetMapping("/list")
    public List<Mypaper> list() {
        List<Mypaper> list = paperService.list();
        return list;
    }
    
    @CrossOrigin
    @GetMapping("/add")
    public String add( Long id, String date, String url, String title, String name) {
        date = date.substring(0,10);
        Mypaper paper = new Mypaper();
        paper.setDate(date);
        paper.setUrl(url);
        paper.setTitle(title);
        paper.setName(name);
        paperService.save(paper);
        return "success";
    }
    
    @CrossOrigin
    @GetMapping("/search")
    public List<Mypaper> search( String title) {
        if (title.isEmpty()) {
            title = "";
        }
        LambdaQueryWrapper<Mypaper> lqw = new LambdaQueryWrapper<>();
        lqw.like(title.isEmpty()!=true,Mypaper::getTitle,title);
        return paperService.list(lqw);
    }
/**
 Access to XMLHttpRequest at '' from origin '' has been blocked by CORS policy: Response to preflight request doesn't pass access control check: No 'Access-Control-Allow-Origin' header is present on the requested resource.
 */

}
