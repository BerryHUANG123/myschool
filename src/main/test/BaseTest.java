import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * base test
 * @author sujinpeng
 * @version 创建时间：2017/12/23 14:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = AbstractDiagnosticFormatter.SimpleConfiguration.class)
@ContextConfiguration(locations = {"classpath*:spring-config.xml"})
public class BaseTest {

    @Test
    public void test(){
        Thread thread =  new Thread();
        System.out.println(thread.getState().name());
        thread.start();
        for(int i=0;i<1000;i++){
            System.out.println(thread.getState().name());
        }
    }

}