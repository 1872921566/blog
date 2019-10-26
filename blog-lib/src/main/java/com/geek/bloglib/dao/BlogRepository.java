package com.geek.bloglib.dao;

import com.geek.bloglib.model.Blog;
import com.geek.bloglib.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Transactional
@Repository
public interface BlogRepository extends JpaRepository<Blog,String>, JpaSpecificationExecutor<Blog> {


    @Query(value = "select b from Blog  b where b.published = true")
    List<Blog> findTop(Pageable pageable);

    @Query(value = "select b from Blog  b where b.published = true")
    Page<Blog> findSite(Pageable pageable);

    @Query(value = "select b from Blog b where b.title like ?1 or b.content like ?1 and b.published = true")
    Page<Blog> findByQuery(String query,Pageable pageable);

    @Transactional
    @Modifying //通知jpa这是一个update或者delete操作，jpql不支持insert操作
    @Query("update Blog b set b.view = b.view+1 where b.id = ?1")
    int updateViews(String id);


    //查询数据库中blog类的 所有创建时间年份 返回一个list集合接收
    //SELECT DATE_FORMAT(create_time,"%Y") as year FROM  blog GROUP BY YEAR ORDER BY  DATE_FORMAT(create_time,"%Y") DESC
    //Jpa 中sql方法需要用function()代入参数使用    %Y为sql中一种格式
    @Query(value = "select function('date_format',b.createTime,'%Y') as year from Blog b group by function('date_format',b.createTime,'%Y') order by year desc ")
    List<String> findGroupYear();

    //SELECT * FROM blog b WHERE date_format(b.create_time,"%Y") = "2019";
    @Query(value = "SELECT b FROM Blog b WHERE function('date_format',b.createTime,'%Y') =?1")
    List<Blog> findByYear(String year);
}
