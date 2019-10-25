package com.geek.bloglib.dao;

import com.geek.bloglib.model.Blog;
import com.geek.bloglib.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BlogRepository extends JpaRepository<Blog,String>, JpaSpecificationExecutor<Blog> {


    @Query(value = "select b from Blog  b where b.published = true")
    List<Blog> findTop(Pageable pageable);

    @Query(value = "select b from Blog  b where b.published = true")
    Page<Blog> findSite(Pageable pageable);

    @Query(value = "select b from Blog b where b.title like ?1 or b.content like ?1 and b.published = true")
    Page<Blog> findByQuery(String query,Pageable pageable);

}
