package com.kajjoy.controller;

import com.kajjoy.service.KajjoyUserRegistrationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class ApiController {

    @Resource
    private KajjoyUserRegistrationService userRegistrationService;

    @GetMapping("/sessionId")
    public String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return uid.toString();
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public void register(@RequestParam String username,@RequestParam String pwd){
        userRegistrationService.register(username,pwd);
    }

}
