package com.geek.blogmain.controllerAdmin;

import com.geek.bloglib.model.Type;
import com.geek.bloglib.service.TypeService;
import com.geek.bloglib.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
@Transactional
public class TypeController {

    @Autowired
    TypeService typeService;

    //查看分类
    @GetMapping("/types")
    public String types(@PageableDefault(size = 8,sort = {"id"}, direction = Sort.Direction.ASC)
                                    Pageable pageable, Model model){

        Page<Type> types = typeService.findAll(pageable);
        model.addAttribute("types",types);
        return "/admin/page/admin_type_types";
    }

    //删除分类
    @GetMapping("/input/delete")
    public String  delete(@RequestParam String id, RedirectAttributes attributes){

        if(typeService.findById(id).getBlogs().size()!=0)
        {
            attributes.addFlashAttribute("message","该分类存在文章，无法删除");
        }else {
            typeService.delete(id);
            attributes.addFlashAttribute("message","成功删除该分类");
        }


        return "redirect:/admin/types";
    }


    //保存分类
    @PostMapping("/type/input")
    public String save(Type type,RedirectAttributes attributes,@RequestParam String id){
        if(id.length()==0){
            type.setId(IDUtils.UUID());
            Type save = typeService.save(type);
            if(save==null){
                attributes.addFlashAttribute("message","操作失败");
            }
            else{
                attributes.addFlashAttribute("message","操作成功");
            }
        }else {
            typeService.update(id,type);
            attributes.addFlashAttribute("message","更新成功");
        }
        return "redirect:/admin/type/toInput";
    }


    //映射页面
    @GetMapping("/type/toInput")
    public String input(Model model){
        model.addAttribute("type", new Type());
        return "admin/page/admin_type_input";
    }

    //跳到更新 页面
    @GetMapping("/input/toUpdate")
    public String toUpdate(@RequestParam String id,Model model){
        Type type = typeService.findById(id);
        model.addAttribute("type",type);
        return "admin/page/admin_type_input";
    }

}
