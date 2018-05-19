import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author :sujinpeng
 * @Date :2018/5/19
 * @Time :16:20
 * @Description :A
 */
public class A implements Callable<List>{
    @Override
    public List<String> call() throws Exception {
        Thread.sleep(new Random().nextInt(5)*1000);
        return Lists.newArrayList("A.1","A.2","A.3");
    }
}
