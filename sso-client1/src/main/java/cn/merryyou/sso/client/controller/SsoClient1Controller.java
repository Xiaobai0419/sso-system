package cn.merryyou.sso.client.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SsoClient1Controller {

    @RequestMapping("/client1/hello")
    public String helloClient1(@RequestParam(name = "user") String name) {
        return "Hi," + name + ",welcome to client1!!";
    }

}
