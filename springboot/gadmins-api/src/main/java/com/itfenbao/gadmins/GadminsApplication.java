package com.itfenbao.gadmins;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableSwagger2Doc
@SpringBootApplication(scanBasePackages = {"com.itfenbao.gadmins"})
public class GadminsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GadminsApplication.class, args);
    }

}
