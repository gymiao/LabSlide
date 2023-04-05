package com.example.labslide.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Mypaper implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String date;
    private String url;
    private String title;
    private String name;
    
}
