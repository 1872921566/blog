package com.geek.blogmain;

import com.geek.bloglib.dao.BlogRepository;
import com.geek.bloglib.model.Blog;
import com.geek.bloglib.model.Type;
import com.geek.bloglib.service.TypeService;
import com.geek.bloglib.util.MD5Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class BlogMainApplicationTests {

    @Autowired
    BlogRepository blogRepository;

    @Test
    public void contextLoads() {
//        Pageable pageable = new PageRequest(1,10, Sort.Direction.DESC);
//        List<Blog> content = blogRepository.findByQuery("", pageable).getContent();
//        System.out.println("content======"+content.toString());
    }

}
