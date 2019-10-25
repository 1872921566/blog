package com.geek.bloglib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    private String id;

    private String nickname;

    private String username;

    private String password;

    private String email;

    //头像
    private String avatar;

    //用户类型
    private Integer type;

    private String createTime;

    private String updateTime;

    @OneToMany(mappedBy = "user")
    private List<Blog> blogs;
}
