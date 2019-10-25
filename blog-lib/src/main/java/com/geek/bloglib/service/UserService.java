package com.geek.bloglib.service;

import com.geek.bloglib.dao.UserRepository;
import com.geek.bloglib.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User checkUser(String username,String password){
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }


}
