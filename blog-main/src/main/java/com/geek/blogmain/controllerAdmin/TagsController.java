package com.geek.blogmain.controllerAdmin;

import com.geek.bloglib.model.Tag;
import com.geek.bloglib.service.TagsService;
import com.geek.bloglib.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.util.List;

@Controller
@RequestMapping("admin")
public class TagsController {

    @Autowired
    TagsService service;
    //-------------Router----------
    @GetMapping("/tag/toInput")
    public String toInput(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/page/admin_tag_input";
    }

//    @GetMapping("/tag/tags")
//    public String toTags(){
//        return "admin/page/admin_tag_tags";
//    }

    //--------------功能----------------

    //查看标签
    @GetMapping("/tag/tags")
    public String  findAll(@PageableDefault(size = 8,sort = {"name"},
            direction = Sort.Direction.ASC) Pageable pageable, Model model){
        Page<Tag> page = service.findAll(pageable);
        model.addAttribute("page",page);
        return "admin/page/admin_tag_tags";
    }

    //删除标签
    @GetMapping("/tag/delete")
    public String  delete(@RequestParam String id, RedirectAttributes attributes){
        service.delete(id);
        attributes.addFlashAttribute("message","成功删除该分类");
        return "redirect:/admin/tag/tags";
    }
    //保存标签
    @PostMapping("/tag/save")
    public String  save(Tag tag,RedirectAttributes attributes,@RequestParam String id){
        if(id.length()==0){
            tag.setId(IDUtils.UUID());
            Tag save = service.save(tag);
            if(save==null){
                attributes.addFlashAttribute("message","标签新建失败");
            }else {
                attributes.addFlashAttribute("message","标签新建成功");
            }
        }else{
            service.update(id,tag);
            attributes.addFlashAttribute("message","标签更新成功");
        }
        return "redirect:/admin/tag/tags";
    }

    //跳到更新 页面
    @GetMapping("tag/toUpdate")
    public String toUpdate(@RequestParam String id,Model model){
        Tag tag = service.findById(id);
        model.addAttribute("tag",tag);
        return "admin/page/admin_tag_input";
    }

    //跳到更新 页面


}
