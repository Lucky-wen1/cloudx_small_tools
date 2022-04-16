package com.example.cloudx_small_tools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.cloudx_small_tools.mapper")
public class CloudxSmallToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudxSmallToolsApplication.class, args);
    }

}
