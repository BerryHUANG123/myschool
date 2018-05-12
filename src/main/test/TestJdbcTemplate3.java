import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author :sujinpeng
 * @Date :2018/5/12
 * @Time :15:16
 * @Description :测试JDBCTEMPLATE3
 */
public class TestJdbcTemplate3 extends BaseTest{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void test1(){
        jdbcTemplate.update("INSERT INTO `t_user` VALUES(?,'黄大大','male',18,now(),now())","我最厉害了");
    }
}
