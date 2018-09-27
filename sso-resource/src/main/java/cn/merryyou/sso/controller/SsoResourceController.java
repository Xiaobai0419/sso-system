package cn.merryyou.sso.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SsoResourceController {

    @RequestMapping("/resource/{id}")
    public String helloClient1(@PathVariable(name = "id") String name) {
        return "Hi," + name + ",welcome to resource server!!";
    }

}
