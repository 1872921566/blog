package com.geek.blogmain.controllerAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class RouteAdminController {


    @RequestMapping("/index")
    public String routeIndex(){
        return "admin/admin_index";
    }

    @RequestMapping("/manager")
    public String routeManager(){
        return "admin/page/admin_manager";
    }

}
