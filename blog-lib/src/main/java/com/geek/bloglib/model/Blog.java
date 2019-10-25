package com.geek.bloglib.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.io.PipedReader;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO) //自动生成id
    private String id;

    //-------实体表之间关系-------//

    @ManyToOne
    private Type type;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();
//    private List<Tag> tags = new ArrayList<>();

    //注解表示 不会在数据库中建立数据
    @Transient
    private String tagIds;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();

    //--------------------------//
    private  String title;

    //描述
    private  String description;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private  String content;

    //首图
    private String firstPicture;

    //标记  : 转载 原创...
    private String flag;

    //浏览次数
    private Integer view;

    //赞赏是否开启
    private boolean appreciation;

    //版权是否开启(转载声明)
    private boolean shareStatement;

    //评论是否开启
    private boolean commentAbled;

    //是否发布  不发布则保存草稿
    private boolean published;

    //是否推荐
    private boolean recommend;

    private String createTime;

    private String updateTime;

    //初始化ids
    public void init(){
        this.tagIds=tagsToIds(this.getTags());
    }

    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
}
