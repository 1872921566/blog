package com.geek.bloglib.dao;

import com.geek.bloglib.model.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TypeRepository extends JpaRepository<Type,String> {
    void deleteById(String id);

    @Query(value = "SELECT * FROM type WHERE name =?1",nativeQuery = true)
    Type findByName(String name);

    @Query(value = "select t from Type  t")
    List<Type> findTop(Pageable pageable);
}
