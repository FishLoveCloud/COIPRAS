package edu.seu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yxl
 * @Date: 2019-05-15 16:22
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
