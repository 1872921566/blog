package com.geek.bloglib.dao;


import com.geek.bloglib.model.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CommentRepository extends JpaRepository<Comment,String> {



    List<Comment> findByBlogIdAndParentCommentNull(String blogId, Sort sort);
}
