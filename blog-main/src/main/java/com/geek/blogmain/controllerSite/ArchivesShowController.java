package com.geek.blogmain.controllerSite;

import com.geek.bloglib.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ArchivesShowController {

    @Autowired
    BlogService blogService;

    @GetMapping("/archives")
    public String routeArchives(Model model){
        model.addAttribute("map",blogService.findByArchives());
        model.addAttribute("blogSize",blogService.findTop(10000).size());
        model.addAttribute("updatePageFooter",blogService.findTop(3));
        return "site/page/archives";
    }
}
