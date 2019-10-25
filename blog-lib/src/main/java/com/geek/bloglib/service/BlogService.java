package com.geek.bloglib.service;

import com.geek.bloglib.bo.BlogSearch;
import com.geek.bloglib.dao.BlogRepository;
import com.geek.bloglib.exception.NotFoundException;
import com.geek.bloglib.model.Blog;

import com.geek.bloglib.util.DateUtils;
import com.geek.bloglib.util.IDUtils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    
    @Autowired
    BlogRepository repository;
    
    public Page<Blog> findByQuery(String query, Pageable pageable){
        return  repository.findByQuery(query,pageable);
    }


    public String save(Blog blog){
        //新建
        if(blog.getId().length()<1){
            blog.setId(IDUtils.UUID());
            blog.setCreateTime(DateUtils.getCurrTime());
            blog.setUpdateTime(DateUtils.getCurrTime());
            blog.setView(0);
            Blog save = repository.save(blog);
            if(save==null){
                return "添加失败";
            }else {
                return "添加成功";
            }
        }
        else {
            blog.setUpdateTime(DateUtils.getCurrTime());
            Blog save = repository.save(blog);
            if(save==null){
                return "更新失败";
            }else {
                return "更新成功";
            }
        }
    }
    
    public void delete(String id){
        repository.deleteById(id);
    } 


    public  Page<Blog> findAll(Pageable pageable){
        return repository.findAll(pageable);
    }

    public  Page<Blog> findSite(Pageable pageable){
        return repository.findSite(pageable);
    }

    public List<Blog> findTop(Integer size){
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable = new PageRequest(0, size, sort);
        return repository.findTop(pageable);
    }

    //组合多条件查询 ,博客标题,博客是否推荐,博客分类 继承JpaSpecificationExecutor<Blog>
    public Page<Blog> findAll(Pageable pageable, BlogSearch blog){
        return  repository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(blog.getTitle())){
                    predicates.add(cb.like(root.get("title").as(String.class), "%"+blog.getTitle()+"%"));
                }
                if(blog.getTypeId()!=null){
                    predicates.add(cb.equal(root.get("type").get("id"),blog.getTypeId()));
                }
                if(blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }
    
    public Blog findById(String id){
        return  repository.findById(id).get();
    }

    public void update(String id,Blog blog){
        Optional<Blog> op = repository.findById(id);
        if(!op.isPresent()){
            throw new NotFoundException("不存在该博客");
        }
        Blog data = op.get();
        BeanUtils.copyProperties(data,blog);
        repository.save(data);
    }

    public long countBlog(){
        Iterable<Blog> count = repository.findAll();
        return  count.spliterator().getExactSizeIfKnown();
    }
}
