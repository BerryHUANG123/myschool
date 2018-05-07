package com.myschool.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import jdk.internal.util.xml.impl.Input;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Berry on 2018/5/2.
 */
public class DBUtils {
    private static JdbcTemplate jdbcTemplate;
    static {
        // JDBC模板依赖于连接池来获得数据的连接，所以必须先要构造连接池
        Properties properties = new Properties();
        InputStream inputStream = null;
        try{
            inputStream = DBUtils.class.getClassLoader().getResourceAsStream("config/jdbc.properties");
            properties.load(inputStream);

        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
         }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        // 创建JDBC模板
        try {
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            jdbcTemplate = new JdbcTemplate(dataSource,false);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        //返回所有的警告异常
        jdbcTemplate.setIgnoreWarnings(false);
    }

    public static JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
