package com.wrial.main;

import com.wrial.main.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class HttpFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest servlet =(HttpServletRequest) servletRequest;
         //打印路径和线程id日志
        log.info("do filter,{},{}",servlet.getServletPath(),Thread.currentThread().getId());
        //将线程Id放在自定义的RequestHolder
        RequestHolder.add(Thread.currentThread().getId());
        filterChain.doFilter(servletRequest,servletResponse);
    }

}
