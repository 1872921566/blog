package com.geek.blogmain.controllerSite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller

public class RouteSiteController {

    @RequestMapping("/about")
    public String routeAbout(){
        return "site/page/about";
    }

    @RequestMapping("/archives")
    public String routeArchives(){
        return "site/page/archives";
    }



    @RequestMapping("/tags")
    public String routeTags(){ return "site/page/tags"; }

    @RequestMapping("/type")
    public String routeType(){ return "site/page/type"; }

}
