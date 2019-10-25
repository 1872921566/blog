package com.geek.bloglib.service;

import com.geek.bloglib.dao.TypeRepository;
import com.geek.bloglib.exception.NotFoundException;
import com.geek.bloglib.model.Type;
import org.hibernate.annotations.NotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    public Type save(Type type){
        return typeRepository.save(type);
    }

    public void delete(String id){
         typeRepository.deleteById(id);
    }

    public void update(String id,Type type){
        Type t = findById(id);
        if(t ==null){
            throw new NotFoundException("分类不存在");
        }
        BeanUtils.copyProperties(type,t);
        typeRepository.save(t);

    }

    public List<Type> findTop(Integer size){
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return typeRepository.findTop(pageable);
    }

    public List<Type> findAll(){
        return typeRepository.findAll();
    }

    public Page<Type> findAll(Pageable pageable){
        return typeRepository.findAll(pageable);
    }

    public Type findById(String id){
        return typeRepository.findById(id).get();
    }

    public Type findByName(String name){ return typeRepository.findByName(name);};
}
