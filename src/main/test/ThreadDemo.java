import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.myschool.dao.UserDao;
import com.myschool.model.User;
import org.apache.commons.lang3.RandomUtils;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.quartz.spi.ThreadExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.LocalTaskExecutorThreadPool;

import javax.xml.rpc.Call;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collector;

/**
 * @author :huangjinyang
 * @Date :2018/5/19
 * @Time :16:20
 * @Description :
 */
public class ThreadDemo extends BaseTest {


    public static void main(String[] args) throws Exception {
        System.out.println(JSON.toJSONString(search("xiaoming")));
    }

    public static List<String> search(String content) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        CompletionService<List> completionService = new ExecutorCompletionService<>(
                exec);
        completionService.submit(new A());
        completionService.submit(new B());
        completionService.submit(new C());
        List allResult = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            List temp = completionService.take().get();
            allResult.add(temp);
        }
        exec.shutdown();
        return allResult;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserDao userDao;

    @Test
    public void test() {

        //List<User> userList = Collections.synchronizedList(Lists.newArrayList());
        List<User> userList = Lists.newArrayList();
        //录入员工
        Runnable myRunnable = () -> {
            try {
                add(userList,generateUser());
                System.out.println("新增一个user,当前集合大小:" + userList.size() + ",随机数是否死锁:" + RandomUtils.nextInt(0, 100));
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1000, 2000, 5, TimeUnit.MINUTES, new SynchronousQueue<>(), Thread::new, (r, executor) -> {
            try {
                Thread.sleep(1000);
                executor.execute(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        long start = System.currentTimeMillis();
        while (true) {
            if (userList.size() >= 10) {
                threadPoolExecutor.shutdown();
                break;
            }
            threadPoolExecutor.execute(myRunnable);
            System.out.println("新增一个任务,当前集合大小:" + userList.size() + ",随机数是否死锁:" + RandomUtils.nextInt(0, 100));
        }

       while (true){
           if(threadPoolExecutor.isTerminated()){
               long end = System.currentTimeMillis();
               System.out.println("耗时" + (end - start) + "MS");
               System.out.println(userList.size());
               break;
           }
       }


    }

    private User generateUser() {
        User user = new User();
        user.setAge(RandomUtils.nextInt(0, 101));
        user.setName(Thread.currentThread().getName());
        user.setSex(null);
        return user;
    }



    public synchronized void add(List list,User user){
        if(list.size()<10){
            list.add(user);
        }
    }


}
