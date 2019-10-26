package com.geek.blogmain.controllerSite;

import com.geek.bloglib.model.Tag;
import com.geek.bloglib.service.BlogService;
import com.geek.bloglib.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TagShowController {

    @Autowired
    TagsService tagsService;

    @Autowired
    BlogService blogService;


    @GetMapping("tag")
    public String tags(@RequestParam String id, @PageableDefault(size = 5,direction = Sort.Direction.DESC,sort = {"updateTime"})Pageable pageable, Model model){
        List<Tag> tags =tagsService.findAll();
        if(id.equals("-1")){
            id = tags.get(0).getId();
        }
        model.addAttribute("page",blogService.findAll(pageable,id));
        model.addAttribute("tags",tags);
        model.addAttribute("id",id);
        model.addAttribute("updatePageFooter",blogService.findTop(3));
        return "site/page/tag";
    }
}
