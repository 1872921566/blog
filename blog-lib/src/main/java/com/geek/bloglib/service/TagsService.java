package com.geek.bloglib.service;

import com.geek.bloglib.dao.TagRepository;
import com.geek.bloglib.exception.NotFoundException;
import com.geek.bloglib.model.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TagsService {
    @Autowired
    TagRepository tagRepository;

    public List<Tag> getAll(String ids){
        return  tagRepository.findAllById(convertToList(ids));
    }

    private List<String> convertToList(String ids) {
        List<String> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(new String(idarray[i]));
            }
        }
        return list;
    }

    public Tag save(Tag tag){
        return tagRepository.save(tag);
    }

    public void delete(String id){
        tagRepository.deleteById(id);
    }
//    public void deleteByIds(String id){
//        tagRepository.delete(id);
//    }

    public List<Tag> findTop(Integer size){
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0, size, sort);
        return tagRepository.findTop(pageable);
    }

    public void update(String id,Tag tag){
        Tag t = findById(id);
        if(t ==null){
            throw new NotFoundException("分类不存在");
        }
        BeanUtils.copyProperties(tag,t);
        tagRepository.save(t);

    }
    public List<Tag> findAll(){return tagRepository.findAll();}

    public Page<Tag> findAll(Pageable pageable){
        return tagRepository.findAll(pageable);
    }

    public Tag findById(String id){
        return tagRepository.findById(id).get();
    }

    public Tag findByName(String name){ return tagRepository.findByName(name);};
}
