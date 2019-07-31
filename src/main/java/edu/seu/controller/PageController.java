package edu.seu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yxl
 * @Date: 2019-05-15 16:22
 */
@Controller
public class PageController {

    @RequestMapping(path={"/","/index"})
    public String index(){ return "index";}

    @RequestMapping("/login")
    public String login(String next,Model model)
    {
        model.addAttribute("next",next);
        return "login";
    }
    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/findPassword")
    public String findPassword() {
        return "findPassword";
    }

    @RequestMapping("/modifyPassword")
    public String modifyPassword(){
        return "modifyPassword";
    }

    @RequestMapping("/updatePassword")
    public String updatePassword(){
        return "updatePassword";
    }

    @RequestMapping("/addCountry")
    public String addCountry(){ return "addCountry";}

}
