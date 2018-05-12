import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.myschool.dao.mapper.UserMapper;
import com.myschool.model.User;
import com.myschool.utils.DBUtils;
import org.apache.commons.collections.bag.SynchronizedSortedBag;
import org.springframework.jdbc.core.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author :huangjinyang
 * @Date :2018/5/12
 * @Time :13:12
 * @Description :测试JdbcTemplate相关方法2
 */
public class TestJdbcTempate2 {
    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = DBUtils.getJdbcTemplate();
        long startTime = System.currentTimeMillis();
        List<Object[]> argsList = Lists.newArrayList();
      for (int i = 0; i < 10; i++) {
            argsList.add(new Object[]{"黄" + i + "号", i % 2 == 0 ? "male" : "female", i + 18});
        }
        int[] batchResult = jdbcTemplate.batchUpdate(
                "INSERT INTO `t_user` (`name`,`sex`,`age`,`create_time`,`update_time`) VALUES (?,?,?,now(),now())",
                argsList
        );
        System.out.println(JSON.toJSONString(batchResult));

      // jdbcTemplate.queryForObject("SELECT * FROM `t_user` WHERE `id` in (4002,4003)",new UserMapper());
       // System.out.println(jdbcTemplate.queryForObject("SELECT * FROM `t_user`",new SingleColumnRowMapper<Integer>()));
       /* List<Map<String,Object>> list = jdbcTemplate.query("SELECT * FROM `t_user`",new ColumnMapRowMapper());

        System.out.println(JSON.toJSONString(list));*/
        long endTime = System.currentTimeMillis();
        System.out.println("执行时间(MS):" + (endTime - startTime));

    }
}
