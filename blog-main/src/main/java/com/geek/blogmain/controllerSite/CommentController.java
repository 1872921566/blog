package com.geek.blogmain.controllerSite;

import com.geek.bloglib.model.Comment;
import com.geek.bloglib.service.BlogService;
import com.geek.bloglib.service.CommentService;
import com.geek.bloglib.util.LogInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    BlogService blogService;

    @Value("${comment.avatar}")
    private String avatar;

    @RequestMapping("/blog")
    public String routeBlog(){
        return "site/page/blog";
    }

//    @GetMapping("/comments/{blogId}/")
//    public String comments(@PathVariable String blogId, Model model){
//        model.addAttribute("comments",commentService.findByBlogId(blogId));
//        return "/blog/detail(id="+blogId+")";
//    }

    //发布留言
    @PostMapping("/comments/save")
    public String post(Comment comment){
        LogInfoUtils.log(comment);
        String blogId = comment.getBlog().getId();
        comment.setBlog(blogService.findById(blogId));
        comment.setAvatar(avatar);
        commentService.save(comment);
        return "redirect:/blog/detail?id="+blogId;
    }
}
