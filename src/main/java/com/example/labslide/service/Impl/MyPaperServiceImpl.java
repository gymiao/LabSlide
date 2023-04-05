package com.example.labslide.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.labslide.domain.Mypaper;
import com.example.labslide.mapper.MyPaperMapper;
import com.example.labslide.service.MyPaperService;
import org.springframework.stereotype.Service;

@Service
public class MyPaperServiceImpl extends ServiceImpl<MyPaperMapper, Mypaper> implements MyPaperService {
}
