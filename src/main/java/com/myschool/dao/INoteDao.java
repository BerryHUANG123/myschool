package com.myschool.dao;

import com.myschool.model.Note;
/**
 * @author :huangjinyang
 * @Date :2018-4-9
 * @Time :20:26:22
 * @Description :
 */
public interface INoteDao {
    Long add(Note note);
    
    Note getById(Integer puid, Long id);

    int update(Note note);

    int deleteById(Integer puid, Integer id);
}