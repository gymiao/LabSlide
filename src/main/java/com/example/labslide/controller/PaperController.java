package com.example.labslide.controller;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.example.labslide.domain.Paper;
import com.example.labslide.service.PaperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/paper")

public class PaperController {
//    @GetMapping
//    public String getString() {
//        return "Hello World!";
//    }
    @Autowired
    private PaperService paperService;
//

    @CrossOrigin
    @GetMapping("/list")
    public List<Paper> list() {
        List<Paper> list = paperService.list();
        return list;
    }
    
    @CrossOrigin
    @GetMapping("/add")
    public String add( Long id, String date, String url, String title, String name) {
        date = date.substring(0,10);
        Paper paper = new Paper();
        paper.setDate(date);
        paper.setUrl(url);
        paper.setTitle(title);
        paper.setName(name);
        paperService.save(paper);
        return "success";
    }
    
    @CrossOrigin
    @GetMapping("/search")
    public List<Paper> search( String title) {
        if (title.isEmpty()) {
            title = "";
        }
        LambdaQueryWrapper<Paper> lqw = new LambdaQueryWrapper<>();
        lqw.like(title.isEmpty()!=true,Paper::getTitle,title);
        return paperService.list(lqw);
    }
/**
 Access to XMLHttpRequest at '' from origin '' has been blocked by CORS policy: Response to preflight request doesn't pass access control check: No 'Access-Control-Allow-Origin' header is present on the requested resource.
 */

}
