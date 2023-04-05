package com.example.labslide.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.labslide.domain.Paper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaperMapper extends BaseMapper<Paper> {
}
