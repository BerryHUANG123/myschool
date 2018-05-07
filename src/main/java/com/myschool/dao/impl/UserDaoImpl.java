package com.myschool.dao.impl;

import com.google.common.collect.Lists;
import com.myschool.dao.BaseDao;
import com.myschool.dao.UserDao;
import com.myschool.model.User;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * @author :sujinpeng
 * @Date :2018/4/8
 * @Time :15:53
 * @Description :
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public int add(User user) {
        final String sql = "insert into " + getTableName() + " (`name`, `sex`, `age`, `create_time`, `update_time`) values(?,?,?,now(),now())";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            ps.setString(i++, user.getName());
            ps.setString(i++, user.getSex());
            ps.setObject(i, user.getAge());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    protected String getTableName() {
        return "`t_user`";
    }
}
