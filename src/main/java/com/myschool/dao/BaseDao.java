package com.myschool.dao;

import com.google.common.collect.Lists;
import com.myschool.model.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : sujinpeng
 * @Date : 2017/12/23
 * @Time : 15:12
 * @Description :
 */
public abstract class BaseDao {
    protected final Logger logger = LogManager.getLogger(this.getClass());

    private final String[] seqTables = {"t_myschool_seq"};

    protected String tableName;

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    /**
     * 子类实现
     * @return tableName
     */
    protected abstract String getTableName();

    protected <T> T get(Integer puid, Long id, String tableName, Class<T> tClass) {
        return get(tableName,new Object[]{id,puid},tClass);
    }

    protected <T> T get(Integer puid, Long id, String tableName, RowMapper<T> rowMapper) {
        return get(tableName,new Object[]{id,puid},rowMapper);
    }

    protected <T> T get(Integer puid, Integer id, String tableName, Class<T> tClass) {
        return get(tableName,new Object[]{id,puid},tClass);
    }

    protected <T> T get(Integer id, String tableName, Class<T> tClass) {
        String sql = String.format("select * from %s where id = ?", tableName);
        List<T> objList = jdbcTemplate.query(sql,new Object[]{id}, new BeanPropertyRowMapper<>(tClass));
        return objList == null || objList.isEmpty() ? null : objList.get(0);
    }

    protected <T> T get(Integer id, String tableName, RowMapper<T> rowMapper) {
        String sql = String.format("select * from %s where id = ?", tableName);
        List<T> objList = jdbcTemplate.query(sql,new Object[]{id}, rowMapper);
        return objList == null || objList.isEmpty() ? null : objList.get(0);
    }

    protected <T> T get(Integer puid, Integer id, String tableName, RowMapper<T> rowMapper) {
        return get(tableName,new Object[]{id,puid},rowMapper);
    }

    private <T> T get(String tableName,Object[] args, Class<T> tClass) {
        String sql = String.format("select * from %s where id = ? and puid = ?", tableName);
        List<T> objList = jdbcTemplate.query(sql,args, new BeanPropertyRowMapper<>(tClass));
        return objList == null || objList.isEmpty() ? null : objList.get(0);
    }

    private <T> T get(String tableName,Object[] args, RowMapper<T> rowMapper) {
        String sql = String.format("select * from %s where id = ? and puid = ?", tableName);
        List<T> objList = jdbcTemplate.query(sql,args, rowMapper);
        return objList == null || objList.isEmpty() ? null : objList.get(0);
    }

    protected <T> T get(String tableName, String whereSql, Object[] args, Class<T> tClass) {
        String sql = String.format("select * from %s %s", tableName, whereSql);
        List<T> objList = jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<T>(tClass));
        return objList == null || objList.isEmpty() ? null : objList.get(0);
    }

    protected <T> T get(String tableName, String whereSql, Object[] args, RowMapper<T> rowMapper) {
        String sql = String.format("select * from %s %s", tableName, whereSql);
        List<T> objList = jdbcTemplate.query(sql, args, rowMapper);
        return objList == null || objList.isEmpty() ? null : objList.get(0);
    }

    protected int delete(Integer id, String table){
        return jdbcTemplate.update(String.format("delete from %s where id = ?",table),id);
    }

    protected int delete(Long id, String table){
        return jdbcTemplate.update(String.format("delete from %s where id = ?",table),id);
    }

    protected int delete(Integer puid, Integer id, String table){
        return jdbcTemplate.update(String.format("delete from %s where id = ? and puid = ?",table),id,puid);
    }

    protected int delete(Integer puid, Long id, String table){
        return jdbcTemplate.update(String.format("delete from %s where id = ? and puid = ?",table),id,puid);
    }

    public int update(String sql, Object[] args) {
        return this.jdbcTemplate.update(sql, args);
    }

    public int batchUpdate(String sql, List<Object[]> batchArgs) {
        int[] a = {};
        if (batchArgs.size() > 0) {
            a = this.jdbcTemplate.batchUpdate(sql, batchArgs);
        }
        return a.length;
    }

    /**
     * 获取一个list集合
     *
     * @param sql
     * @param values
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> getList(String sql, Object[] values, Class<T> tClass) {

        return jdbcTemplate.query(sql, values, new BeanPropertyRowMapper<T>(tClass));
    }

    /**
     * 获取一个list集合
     * @param sql     sql
     * @param values  参数
     * @param <T>     mapper
     * @return        list
     */
    public <T> List<T> getList(String sql, Object[] values, RowMapper<T> rowMapper) {
        return jdbcTemplate.query(sql, values, rowMapper);
    }

    /**
     * 功能:得到共有多少条记录
     * @param countSql : 用来统计的sql语句
     * @param countValues : 统计sql中的变量值
     * @return 共有多少条符合条件的记录
     */
    public Integer getTotalSize(JdbcTemplate jdbcTemplate, String countSql, Object[] countValues){
        Integer totalSize;
        try {
            if(countValues != null){
                totalSize = jdbcTemplate.queryForObject(countSql, countValues, Integer.class);
            }else{
                totalSize = jdbcTemplate.queryForObject(countSql, Integer.class);
            }
        } catch (Exception e) {
            logger.error("得到分页结果的总页数时错误:"+countSql,e);
            return 0;
        }
        return totalSize;
    }

    /**
     * 更新表
     *
     * @param tableName :	表名
     * @param params    :   字段和对应的值
     *                  注:如果字段需要用数据库函数now()，把字段值设置为字符串"now()"
     * @param whereSql  :	条件语句　如" where uid=1"
     * @return int
     */
    protected int updateTable(String tableName, Map<String, Object> params, String whereSql) {
        int t = 0;
        StringBuilder sql = new StringBuilder("update " + tableName + " set ");

        StringBuilder sb = new StringBuilder();
        List<Object> argsList = new ArrayList<>();
        for (String key : params.keySet()) {
            Object value = params.get(key);
            if (sb.length() > 0) {
                sb.append(", ");
            }
            if (String.valueOf(value).equals("now()")) {
                sb.append(key).append("=now()");
            } else {
                sb.append(key).append("=?");
                argsList.add(value);
            }
        }
        if (sb.length() > 0) {
            sql.append(sb.toString());
            sql.append(" ").append(whereSql);
            t = this.update(sql.toString(), argsList.toArray());
        }
        return t;
    }

    /**
     * 功能：得到分页查询的结果
     * @param pageNo ： 当前是第几页
     * @param pageSize ： 每页显示多少条数据
     * @param countSql ： 用来统计共有多少条数据的HQL
     * @param countValues : 统计HQL中的变量值（是一个对象数组）
     * @param sql ： 用来查询的正常HQL
     * @param values ： 用来查询的HQL的变量值（是一个对象数组）
     * @return
     *  返回的是一个HashMap ,包含： 当前页、共多少页、当前页的查询结果List（可能是类，也可能是一个数组）
     */
    protected  <T> Page<T> getPageResult(int pageNo, int pageSize, String countSql, Object[] countValues, String sql, Object[] values, RowMapper<T> rowMapper) {
        List<T> list = Lists.newArrayList();

        //总记录数
        int totalSize= getTotalSize(jdbcTemplate,countSql,countValues);
        //总页数
        // 找出当前要显示页(pageNo)的开始记录号"start"和结束记录号"end",以便只把当前页的数据给找出来 ******/
        int totalPage = (totalSize - 1) / pageSize + 1;
        //如果当前页大于总页数,则显示最后一页
        pageNo = pageNo > totalPage ? totalPage : pageNo;
        //如果当前页小于0,则显示第一页
        pageNo = pageNo < 1 ? 1 : pageNo;
        //记录开始值
        int start = pageSize*(pageNo-1);
        // 如果sql参数为空，不取结果，表示只取count数
        if(!StringUtils.isEmpty(sql)){
            list = getCurrentPageResult(jdbcTemplate, start,pageSize,sql,values,rowMapper);
        }
        return new Page<>(pageNo, pageSize,totalPage,totalSize, list);
    }

    /**
     * 功能：得到分页查询的结果
     * @param pageNo ： 当前是第几页
     * @param pageSize ： 每页显示多少条数据
     * @param countSql ： 用来统计共有多少条数据的HQL
     * @param countValues : 统计HQL中的变量值（是一个对象数组）
     * @param sql ： 用来查询的正常HQL
     * @param values ： 用来查询的HQL的变量值（是一个对象数组）
     * @return
     *  返回的是一个HashMap ,包含： 当前页、共多少页、当前页的查询结果List（可能是类，也可能是一个数组）
     */
    protected  <T> Page<T> getPageResult(int pageNo, int pageSize, String countSql, Object[] countValues, String sql, Object[] values, Class<T> tClass) {
        List<T> list = Lists.newArrayList();

        //总记录数
        int totalSize= getTotalSize(jdbcTemplate,countSql,countValues);
        //总页数
        // 找出当前要显示页(pageNo)的开始记录号"start"和结束记录号"end",以便只把当前页的数据给找出来 ******/
        int totalPage = (totalSize - 1) / pageSize + 1;
        //如果当前页大于总页数,则显示最后一页
        pageNo = pageNo > totalPage ? totalPage : pageNo;
        //如果当前页小于0,则显示第一页
        pageNo = pageNo < 1 ? 1 : pageNo;
        //记录开始值
        int start = pageSize*(pageNo-1);
        // 如果sql参数为空，不取结果，表示只取count数
        if(!StringUtils.isEmpty(sql)){
            list = getCurrentPageResult(jdbcTemplate, start,pageSize,sql,values,new BeanPropertyRowMapper<T>(tClass));
        }
        return new Page<>(pageNo, pageSize,totalPage,totalSize, list);
    }

    /**
     * 功能:得到分页的结果(当前页)
     * @param start : 开始记录号
     * @param pageSize : 每页显示多少条记录
     * @param sql : 要查询的sql语句
     * @param values : sql语句中的变量值
     * @return List
     */
    private <T> List<T> getCurrentPageResult(JdbcTemplate jdbcTemplate, Integer start, Integer pageSize,String sql, Object[] values, RowMapper<T> rowMapper){
        sql = String.format("%s limit %d,%d", sql, start, pageSize);
        List<T> list;
        try {
            if(rowMapper==null){
                if(values == null || values.length == 0){
                    list = (List)jdbcTemplate.queryForList(sql);
                }else{
                    list = (List)jdbcTemplate.queryForList(sql, values);
                }
            } else if(values == null || values.length == 0){
                list = jdbcTemplate.query(sql, rowMapper);
            }else{
                list = jdbcTemplate.query(sql, values, rowMapper);
            }
        } catch (Exception e) {
            logger.error("得到分页结果的当前页时错误:"+sql,e);
            return Lists.newArrayList();
        }
        return list;
    }

    /**
     * 根据用户ID得到序列号
     *
     * @param puid puid
     * @return Long
     */
    public Long getId(Integer puid, int seqTableIndex) {
        String tableName = seqTables[seqTableIndex];
        Long seq = getSequence(tableName);
        // 根据puid和序列号得到新的ID，规则：
        // 	puid左移36左，与序列号按位或，得到新的id
        //  新的id通过右移36左，即可得到用户的puid，方便使用
        //  例: puid = id >> 36;
        return (Long.valueOf(puid) << 36) | seq;
    }

    public synchronized Long getSequence(String table) {
        Long id = null;
        final String sql = "REPLACE INTO " + table + " (stub) VALUES ('S')";

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = (PreparedStatement) connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                return ps;
            }
        }, keyHolder);

        List<Map<String, Object>> list = keyHolder.getKeyList();
        if (list.size() > 0) {
            Map<String, Object> map = list.get(0);
            id = (Long) map.get("GENERATED_KEY");
        }
        return id;
    }
}
