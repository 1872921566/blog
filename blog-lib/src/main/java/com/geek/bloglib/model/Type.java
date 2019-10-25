package com.geek.bloglib.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Type {

    @Id
    private String id;

    @NotBlank(message = "Admin:请输入分类名称")
    private  String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs =new ArrayList<>();
//    private List<Blog> blogs = new ArrayList<>();

}
