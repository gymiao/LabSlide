package com.example.labslide.Filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "filter",urlPatterns = "/*")
public class filter implements Filter {
    @Override
    
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //System.out.println(request.getContentType());
        HttpServletResponse response = (HttpServletResponse) servletResponse;

//为所有请求添加响应头，以解决ajax跨域问题
        
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PATCH, DELETE, PUT, OPTIONS");

//设置编码
        
        response.setCharacterEncoding("UTF-8");
        
        request.setCharacterEncoding("UTF-8");
        
        filterChain.doFilter(request, response);
        
    }

}




