package com.geek.bloglib.dao;

import com.geek.bloglib.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUsernameAndPassword(String username,String password);
}
