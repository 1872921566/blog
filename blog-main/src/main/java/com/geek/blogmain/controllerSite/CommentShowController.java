package com.geek.blogmain.controllerSite;

import com.geek.bloglib.model.Comment;
import com.geek.bloglib.model.User;
import com.geek.bloglib.service.BlogService;
import com.geek.bloglib.service.CommentService;
import com.geek.bloglib.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class CommentShowController {

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
    public String post(Comment comment, HttpSession session){
        String test;
        LogUtils.info(comment);
        String blogId = comment.getBlog().getId();
        comment.setBlog(blogService.findById(blogId));
        comment.setAvatar(avatar);
        User user = (User)session.getAttribute("user");
        if(user!=null){
            comment.setAvatar(user.getAvatar());
            comment.setAdmin(true);
            comment.setNickname(user.getNickname());
        }

        commentService.save(comment);
        return "redirect:/blog/detail?id="+blogId;
    }
}
