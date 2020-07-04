package com.itfenbao.gadmins.app;

import com.itfenbao.gadmins.config.AppConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"APP测试"})
@RestController
@RequestMapping(AppConfig.AppRoute.ROOT + "/test")
public class AppTestController {

    @ApiOperation("")
    @GetMapping("测试")
    public String test() {
        return "test";
    }
}
