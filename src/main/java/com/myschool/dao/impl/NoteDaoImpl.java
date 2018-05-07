package com.myschool.dao.impl;

import java.util.List;

import com.google.common.collect.Lists;

import java.util.Map;

import com.myschool.dao.BaseDao;
import com.myschool.dao.INoteDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Repository;

import com.myschool.model.Note;

/**
 * @author :huangjinyang
 * @Date :2018-4-9
 * @Time :20:26:22
 * @Description :
 */
@Repository
public class NoteDaoImpl extends BaseDao implements INoteDao {

    private static Logger logger = LoggerFactory.getLogger(NoteDaoImpl.class);

    @Override
    public Long add(Note note) {
        note.setId(getId(note.getPuid(), 0));
        String sql = "insert into " + getTableName() + " (`id`,`puid`,`type`,`title`,`context`,`note_time`,`create_time`,`update_time`) " +
                "values ( ?, ?, ?, ?, ?, ?,now(),now())";
        List<Object> argsList = Lists.newArrayList();
        argsList.add(note.getId());
        argsList.add(note.getPuid());
        argsList.add(note.getType());
        argsList.add(note.getTitle());
        argsList.add(note.getContext());
        argsList.add(note.getNoteTime());
        int t = jdbcTemplate.update(sql, argsList.toArray());
        return t > 0 ? note.getId() : 0L;
    }

    @Override
    public Note getById(Integer puid, Long id) {
        return get(puid, id, getTableName(), Note.class);
    }

    @Override
    public int update(Note note) {
        return updateTable(getTableName(), getParamMap(note), " where `id` = ?");
    }

    private Map<String, Object> getParamMap(Note note) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("`type`", note.getType());
        params.put("`title`", note.getTitle());
        params.put("`context`", note.getContext());
        params.put("`note_time`", note.getNoteTime());
        params.put("`update_time`", "now()");
        return params;
    }

    @Override
    public int deleteById(Integer puid, Integer id) {
        return delete(puid, id, getTableName());
    }

    @Override
    protected String getTableName() {
        return "`t_note`";
    }
}