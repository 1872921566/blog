package com.geek.bloglib.dao;


import com.geek.bloglib.model.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TagRepository extends JpaRepository<Tag,String> {
    Tag findByName(String name);

    @Query(value = "select t from Tag  t")
    List<Tag> findTop(Pageable pageable);
}
