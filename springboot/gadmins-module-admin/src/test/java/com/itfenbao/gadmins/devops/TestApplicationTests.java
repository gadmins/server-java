package com.itfenbao.gadmins.admin;

import com.itfenbao.gadmins.admin.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestApplication.class})
public class TestApplicationTests {

    @Autowired
    IAccountService accountService;

    @Test
    public void test() {
        System.out.println("test:" + accountService);
    }
}
