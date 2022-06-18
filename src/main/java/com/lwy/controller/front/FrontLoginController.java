
package com.lwy.controller.front;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class FrontLoginController {
    @GetMapping("/front/login")
    public String login(HttpSession session){
        //session.invalidate();               //让user失效

        if(session.getAttribute("user") != null){
            return "after/index";
        }

        return "front/login";
    }
}