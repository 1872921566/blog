package com.geek.blogmain;

import com.geek.bloglib.dao.BlogRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BlogMainApplicationTests {

    @Autowired
    BlogRepository blogRepository;

    @Test
    public void contextLoads() {
//        Pageable pageable = new PageRequest(1,10, Sort.Direction.DESC);
//        List<Blog> content = blogRepository.findByQuery("", pageable).getContent();
//        System.out.println("content======"+content.toString());

//        List<String> groupYear = blogRepository.findGroupYear();
//        LogInfoUtils.log(groupYear);

//        List<Blog> year = blogRepository.findByYear("2019");
//        log.info("year======="+year.get(0).getTitle());
    }

}
