import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.myschool.dao.mapper.UserMapper;
import com.myschool.model.User;
import com.myschool.utils.DBUtils;
import com.myschool.utils.DateUtil;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.SQLWarningException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.SQLWarning;
import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Berry on 2018/5/1.
 */
public class TestJDBCTemplate {

    private static UserMapper mapper = new UserMapper();

    public static void main(String[] args) throws Exception {
        /*********************************************3.获取jdbcTemplate对象*********************************************/
        JdbcTemplate jdbcTemplate = DBUtils.getJdbcTemplate();
        long startTime = System.currentTimeMillis();

        /*****************************************4.设置JdbcTemplate的最大行数*****************************************/
        //void setMaxRows(int maxRows);  注意:只对查询返回结果集有效,对增删改无效.
        jdbcTemplate.setMaxRows(2);


        /************************************************7.7 执行SQL语句************************************************/
        /******************************************7.1 DDL(ALTER,DROP,CREATE)*******************************************/
        //void execute（String sql）;
        /*jdbcTemplate.execute("CREATE TABLE `t_new_table"+RandomUtils.nextInt(10,50)+"` (`id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,`name` VARCHAR(50) NOT NULL)");//推荐
        int updateResult = jdbcTemplate.update("CREATE TABLE `t_new_table"+RandomUtils.nextInt(10,50)+"` (`id` INT AUTO_INCREMENT NOT NULL PRIMARY KEY,`name` VARCHAR(50) NOT NULL)");//不推荐
        System.out.println(updateResult);*/


        /************************************************7.2 DML(CRUD)**************************************************/

        /******************************************(1)INSERT,DELETE,UPDATE**********************************************/
        /***********************************************(1.1) 单处理****************************************************/
        //int update(String sql,Object[] args);
        //int update(String sql,Object... args);
        //int update(String sql, Object[] args, int[] argTypes);

        /*//int affectRows = jdbcTemplate.update("UPDATE `t_user` SET `name` = '黄先生',`age` = 17,`sex` = 'male'");
        //int affectRows = jdbcTemplate.update("UPDATE `t_user` SET `name` = '黄先生',`age` = 17,`sex` = 'male' WHERE `id` IN(?,?)",new Object[]{1,2},new int[]{Types.INTEGER, Types.INTEGER});
        System.out.println("受影响的行数为:"+ affectRows);*/

        /***********************************************(1.2) 批处理****************************************************/
        /**
         * url加入 &rewriteBatchedStatements=true 参数后
         * batchUpdate操作对于update,delete均返回影响的行数
         * 但是对于insert操作,成功返回-2,失败返回?
         */
        //int[] batchUpdate(String... sql);
        //int[] batchUpdate(String sql,List <Object []> batchArgs);
        //int[] batchUpdate(String sql,List <Object []>batchArgs,int [] argTypes);

        /*int[] batchResult = jdbcTemplate.batchUpdate(
                "INSERT INTO `t_user` (`name`,`sex`,`age`,`create_time`,`update_time`) VALUES ('我爱你0','male',1,now(),now())",
                "INSERT INTO `t_user` (`name`,`sex`,`age`,`create_time`,`update_time`) VALUES ('我爱你1','male',2,now(),now())",
                "INSERT INTO `t_user` (`name`,`sex`,`age`,`create_time`,`update_time`) VALUES ('我爱你2','male',3,now(),now())",
                "DELETE FROM `t_user` WHERE  `name` LIKE '我爱你%'"
          System.out.println(JSON.toJSONString(batchResult));
        );*/


        /*List<Object[]> argsList = Lists.newArrayList();
        for (int i = 0; i < 1000; i++) {
            argsList.add(new Object[]{"黄" + i + "号", i % 2 == 0 ? "male" : "female", i + 18});
        }
        int[] batchResult = jdbcTemplate.batchUpdate(
                "INSERT INTO `t_user` (`name`,`sex`,`age`,`create_time`,`update_time`) VALUES (?,?,?,now(),now())",
                argsList
        );
        System.out.println(JSON.toJSONString(batchResult));*/


        /*List<Object[]> argsList = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            argsList.add(new Object[]{"黄" + i + "号", i % 2 == 0 ? "male" : "female", i + 18});
        }
        int[] typeArr = new int[]{Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        int[] batchResult = jdbcTemplate.batchUpdate(
                "INSERT INTO `t_user` (`name`,`sex`,`age`,`create_time`,`update_time`) VALUES (?,?,?,now(),now())",
                argsList,
                typeArr
        );
        System.out.println(JSON.toJSONString(batchResult));*/

        List<Object[]> argsList = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            argsList.add(new Object[]{"黄0号"});
        }
        int[] typeArr = new int[]{Types.VARCHAR};
        int[] batchResult = jdbcTemplate.batchUpdate(
                "DELETE FROM `t_user` WHERE `name` = ?",
                argsList,
                typeArr
        );
        System.out.println(JSON.toJSONString(batchResult));

       /* List<Object[]> argsList = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            argsList.add(new Object[]{"黄"+i+"号","我要打死你"});
        }
        int[] typeArr = new int[]{Types.VARCHAR,Types.VARCHAR};
        int[] batchResult = jdbcTemplate.batchUpdate(
                "UPDATE `t_user` SET `name` = ? WHERE `name` = ?",
                argsList,
                typeArr
        );
        System.out.println(JSON.toJSONString(batchResult));*/


        /***********************************************(2)SELECT*******************************************************/
        //queryForMap方法实质调用queryForObject,是否抛出空结果集和>1行的异常? 结论:是的,会抛出
        //jdbcTemplate.queryForMap("SELECT * FROM `t_user` WHERE `id` = 10000");
        //jdbcTemplate.queryForMap("SELECT * FROM `t_user` WHERE `id` IN(2,3)");

        //BeanPropertyMapper ColumnMapRowMapper SingleColumnRowMapper

        //BeanPropertyMapper->映射成复杂对象 ColumnMapRowMapper->多列映射成Map SingleColumnRowMapper->单列映射
        //List<T> query     ->多行
        //T queryForObject  ->单行
        //返回单行
        //单行单列
        //String sql = "SELECT `create_time` FROM `t_user` WHERE `id` = 2";
        //Date dateResult = jdbcTemplate.queryForObject(sql, new SingleColumnRowMapper<>());
        //Date dateResult = jdbcTemplate.queryForObject(sql,Date.class);
        //System.out.println(JSON.toJSONString(DateUtil.format(dateResult)));
        //单行多列
        //String sql = "SELECT * FROM `t_user` WHERE `id` = 2";
        //Map<String,Object> resultMap = jdbcTemplate.queryForObject(sql,new ColumnMapRowMapper());
        //System.out.println(JSON.toJSONString(resultMap));
        //User user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<>(User.class));
        //System.out.println(JSON.toJSONString(user));
        //返回多行
        //多行单列
        //String sql = "SELECT `create_time` FROM `t_user` WHERE `id` IN(2,3)";
        //List<Date> resultList = jdbcTemplate.query(sql,new SingleColumnRowMapper<>());
        //System.out.println(JSON.toJSONString(resultList));
        //多行多列
        //String sql = "SELECT * FROM `t_user` WHERE `id` IN(2,3)";
        //List<Map<String,Object>> mapList = jdbcTemplate.query(sql,new ColumnMapRowMapper());
        //System.out.println(JSON.toJSONString(mapList));
        //List<User> userList = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class));
        //System.out.println(JSON.toJSONString(userList));

        //queryForObject和query的衍生方法
        //queryForObject的衍生方法:queryForMap
        //query的衍生方法:.queryForList
        long endTime = System.currentTimeMillis();
        System.out.println("执行SQL耗时: " + (endTime - startTime) + " MS");
    }
}
