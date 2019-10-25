package com.geek.blogmain.controllerAdmin;

import com.geek.bloglib.bo.BlogSearch;
import com.geek.bloglib.model.Blog;
import com.geek.bloglib.model.Type;
import com.geek.bloglib.model.User;
import com.geek.bloglib.service.BlogService;
import com.geek.bloglib.service.TagsService;
import com.geek.bloglib.service.TypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagsService tagsService;

    //映射路径emm   方便
    private static final String MANEGER = "admin/page/admin_manager";

    @GetMapping("/blog/toUpdate")
    public String toUpdate(Model model,@RequestParam String id){
        model.addAttribute("tags",tagsService.findAll());
        model.addAttribute("types",typeService.findAll());
        Blog blog = blogService.findById(id);
        blog.init();
        model.addAttribute("blog",blog);
        return "admin/page/admin_input";
    }

    @GetMapping("/blog/delete")
    public String delete(@RequestParam String id,@RequestParam String tagIds,RedirectAttributes model){
        blogService.delete(id);
        model.addFlashAttribute("message","删除成功");
        return "redirect:/admin/blog/blogs" ;
    }

    @GetMapping("/blog/blogs")
    public String findAll(Model model,
                          @PageableDefault(size = 8,sort = {"view","updateTime"},direction = Sort.Direction.ASC)Pageable pageable, BlogSearch search){
        model.addAttribute("types",typeService.findAll());
        model.addAttribute("page",blogService.findAll(pageable));
        return MANEGER ;
    }

    @PostMapping ("/blog/search")
    public String search(Model model,
                          @PageableDefault(size = 8,sort = {"updateTime"},direction = Sort.Direction.ASC)Pageable pageable, BlogSearch search){
        //判断search 是否为空，空则查询全部
        if(search.getTitle().isEmpty()&&search.getTypeId().isEmpty()&&search.isRecommend()==false){
            model.addAttribute("page",blogService.findAll(pageable));
            return "admin/page/admin_manager :: blogList";
        }
        model.addAttribute("page",blogService.findAll(pageable, search));
        return "admin/page/admin_manager :: blogList";
    }



    @PostMapping("/blog/save")
    public String save(Blog blog, HttpSession session, RedirectAttributes attributes){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.findById(blog.getType().getId()));
        blog.setTags(tagsService.getAll(blog.getTagIds()));

        String msg = blogService.save(blog);
        attributes.addFlashAttribute("message",msg);

        return "redirect:/admin/blog/blogs";
    }

    @GetMapping("/input")
    public String routeInput(Model model){
        model.addAttribute("tags",tagsService.findAll());
        model.addAttribute("types",typeService.findAll());
        Blog blog = new Blog();
        blog.setType(new Type());
        model.addAttribute("blog",blog);
        return "admin/page/admin_input";
    }

}
