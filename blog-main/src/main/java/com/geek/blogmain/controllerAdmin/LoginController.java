package com.geek.blogmain.controllerAdmin;

import com.geek.bloglib.model.User;
import com.geek.bloglib.service.UserService;
import com.geek.bloglib.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserService userService;

    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes){
        User user = userService.checkUser(username, MD5Utils.code(password));
        if(user !=null){
            //防止他人拿到密码
            user.setPassword(null);
            session.setAttribute("user",user);
            return "admin/admin_index";
        }else {
            //重定向之后 RedirectAttributes 还是可以取数据 ，Model 不行 Google一下嘻嘻
            attributes.addFlashAttribute("message","用户名和密码错误，请重新登录");
            return "redirect:/admin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
