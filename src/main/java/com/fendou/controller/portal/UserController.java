package com.fendou.controller.portal;

import com.fendou.commen.ConstValue;
import com.fendou.commen.ServerResponse;
import com.fendou.pojo.UserInfo;
import com.fendou.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @RequestMapping("/login")
    public ServerResponse checkLogin(HttpSession httpSession,String username, String password){
        ServerResponse serverResponse = userService.login(username, password);
        httpSession.setAttribute(ConstValue.CURRENT_USER, serverResponse);
        return serverResponse;
    }

    @RequestMapping("/getQuestionByUsername")
    public ServerResponse getQuestionByUsername(String username){
        ServerResponse serverResponse = userService.getQuestionByUsername(username);
        return serverResponse;
    }


    @RequestMapping("/checkAnswerAndPassword")
    public ServerResponse checkAnswerAndPassword(String username,String question,String answer){
        ServerResponse serverResponse = userService.checkAnswerAndPassword(username,question,answer);
        return serverResponse;
    }


    @RequestMapping("/registerUser")
    public ServerResponse registerUser(UserInfo userInfo){
        ServerResponse serverResponse = userService.registerUser(userInfo);
        return serverResponse;
    }


    @RequestMapping("/updatePassword")
    public ServerResponse updatePassword(String username,String newPassword,String token){
        ServerResponse serverResponse = userService.updatePassword(username,newPassword,token);
        return serverResponse;
    }
}
