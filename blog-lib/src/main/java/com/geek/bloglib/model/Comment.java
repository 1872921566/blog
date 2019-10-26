package com.geek.bloglib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment{

    @Id
    private String id;

    private String nickname;

    private String email;

    private String content;

    //头像
    private String avatar;

    //是否是管理员
    private boolean isAdmin;

    //@Temporal(TemporalType.TIMESTAMP) 生成日期时间
    private String createTime;

    @ManyToOne
    private Blog blog;

    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments;

    @ManyToOne
    private Comment parentComment;
}
