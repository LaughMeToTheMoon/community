package com.sjl.communitytest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: Mr.S
 * @create: 2019-08-04 09:57
 **/
@Controller
public class HelloController
{
    @GetMapping("/")
    public String hello(@RequestParam(name = "name", required = false) String name, Model model)
    {
        model.addAttribute("name", name);

        return "index";
    }
}
