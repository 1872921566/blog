package com.geek.blogmain.controllerSite;


import com.geek.bloglib.bo.BlogSearch;
import com.geek.bloglib.model.Type;
import com.geek.bloglib.service.BlogService;
import com.geek.bloglib.service.TypeService;
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
public class TypeShowController {

    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;

    @GetMapping("/type")
    public String findById(@RequestParam String id, Model model,
                           @PageableDefault(size = 5,direction = Sort.Direction.DESC,sort = {"updateTime"})Pageable pageable){
        //限制查出来只有size个 10000相当于查看全部
        List<Type> types = typeService.findTop(10000);
        //无id值过来时，前端默认传id为-1
        if(id.equals("-1")){
            //默认第一个分类
            id= types.get(0).getId();
        }
        model.addAttribute("types",types);
        BlogSearch search = new BlogSearch();
        search.setTypeId(id);
        model.addAttribute("page",blogService.findAll(pageable,search));
        model.addAttribute("id",id);
        model.addAttribute("updatePageFooter",blogService.findTop(3));
        return "site/page/type";
    }



}
