package com.geek.blogmain.controllerSite;


import com.geek.bloglib.model.Blog;
import com.geek.bloglib.service.BlogService;
import com.geek.bloglib.service.CommentService;
import com.geek.bloglib.service.TagsService;
import com.geek.bloglib.service.TypeService;
import com.geek.bloglib.util.LogUtils;
import com.geek.bloglib.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexShowController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    TagsService tagsService;

    @Autowired
    CommentService commentService;

    @GetMapping("/blog/detail")
    public String toDetail(@RequestParam String id, Model model){
        Blog blog = blogService.findById(id);
        String content = blog.getContent();
        content = MarkdownUtils.markdownToHtmlExtensions(content);
        blog.setContent(content);
        model.addAttribute("comments", commentService.findByBlogId(id));
        model.addAttribute("updatePageFooter",blogService.findTop(3));
//        model.addAttribute("comments",blog.getComments());
        model.addAttribute("blog",blog);
        return "site/page/blog";
    }

    //前端全局搜索 ==>通过标题或内容中是否包含关键词
    @GetMapping("/search")
    public String search(@RequestParam String query, Model model , @PageableDefault
            (size = 5,sort = {"view"},direction = Sort.Direction.ASC) Pageable pageable){
        System.out.println("query====="+query);
        model.addAttribute("page",blogService.findByQuery("%"+query+"%",pageable));
        model.addAttribute("updatePageFooter",blogService.findTop(3));
        model.addAttribute("query",query);
        return "site/page/search";
    }

    @GetMapping("/index")
    public String routeIndex(Model model , RedirectAttributes attributes, @PageableDefault(size = 5,sort = {"view","updateTime"},direction = Sort.Direction.ASC) Pageable pageable){
        model.addAttribute("viewPage",blogService.findSite(pageable));
        model.addAttribute("updatePage",blogService.findTop(6));
        model.addAttribute("updatePageFooter",blogService.findTop(3));
        model.addAttribute("types",typeService.findTop(6));
        model.addAttribute("tags",tagsService.findTop(10));
        model.addAttribute("count",blogService.countBlog());
        return "site/index";
    }


    @RequestMapping("/about")
    public String routeAbout(Model model){
        model.addAttribute("updatePageFooter",blogService.findTop(3));
        return "site/page/about";
    }
}
