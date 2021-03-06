package com.itfenbao.gadmins;

import com.spring4all.swagger.EnableSwagger2Doc;
import net.hasor.spring.boot.EnableHasor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableCaching
@EnableAsync
@EnableSwagger2Doc
@EnableHasor
@MapperScan(basePackages = {"com.itfenbao.gadmins.**.mapper"})
@SpringBootApplication(scanBasePackages = {"com.itfenbao.gadmins"})
@ServletComponentScan(basePackages = {"com.itfenbao.gadmins"})
public class GadminsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GadminsApplication.class, args);
    }

}
