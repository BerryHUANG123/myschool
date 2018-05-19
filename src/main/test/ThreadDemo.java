import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import javax.xml.rpc.Call;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author :huangjinyang
 * @Date :2018/5/19
 * @Time :16:20
 * @Description :
 */
public class ThreadDemo {


    public static void main(String[] args) throws Exception {
        System.out.println(JSON.toJSONString(search("xiaoming")));
    }

    public static List<String> search(String content)throws Exception{
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

}
