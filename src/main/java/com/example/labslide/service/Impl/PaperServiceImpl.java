package com.example.labslide.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.labslide.domain.Paper;
import com.example.labslide.mapper.PaperMapper;
import com.example.labslide.service.PaperService;
import org.springframework.stereotype.Service;

@Service
public class PaperServiceImpl extends ServiceImpl<PaperMapper, Paper> implements PaperService {
}
