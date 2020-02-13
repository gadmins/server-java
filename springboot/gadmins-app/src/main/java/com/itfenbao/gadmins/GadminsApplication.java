package com.itfenbao.gadmins;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.itfenbao.gadmins.**.mapper")
public class GadminsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GadminsApplication.class, args);
    }

}
