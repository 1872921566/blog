package com.geek.bloglib.service;

import com.geek.bloglib.dao.CommentRepository;
import com.geek.bloglib.model.Comment;
import com.geek.bloglib.util.DateUtils;
import com.geek.bloglib.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> findByBlogId(String blogId){
        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        return commentRepository.findByBlogId(blogId,sort);
    }
     
    public Comment save(Comment comment){
        String parentCommentId = comment.getParentComment().getId();
        if(!parentCommentId.equals("-1")){
            comment.setParentComment(commentRepository.findById(parentCommentId).get());
        }else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(DateUtils.getCurrTime());
        comment.setId(IDUtils.UUID());
        return commentRepository.save(comment);
    }

}
