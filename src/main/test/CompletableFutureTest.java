import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.toList;

/**
 * @author :huangjinyang
 * @Date :2018/8/1
 * @Time :21:31
 * @Description :
 */
public class CompletableFutureTest {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);


    public static void main(String[] args) {

        //SynchronousQueue synchronousQueue = new SynchronousQueue<>();
        LinkedBlockingQueue synchronousQueue = new LinkedBlockingQueue(250);
        ThreadPoolExecutor pcThreadPoolExecutor =
                new ThreadPoolExecutor(100, 100, 1, TimeUnit.MINUTES, synchronousQueue,(r, executor) -> {
                    System.out.println("满了满了!!!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    executor.submit(r);
                });

        //多开快速升级游戏等级
        List<CompletableFuture> pcFutureList = Lists.newArrayList();
        for (int i = 0; i < 500; i++) {
            System.out.println(i);
            pcFutureList.add(CompletableFuture.runAsync(() -> play(50), pcThreadPoolExecutor));
        }
        CompletableFuture resultFuture = CompletableFuture.allOf(pcFutureList.toArray(new CompletableFuture[0]));
        new Thread(()->{
            while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
                if (resultFuture.isDone()) {
                    pcThreadPoolExecutor.shutdown();
                    System.out.println(Thread.currentThread().getName() + ":我的游戏等级:" + atomicInteger.get());
                    break;
                }
                System.out.println(Thread.currentThread().getName() + ":真慢啊!还没升级完?! 当前队列大小:" + synchronousQueue.size());
            }
        }).start();


    }

    //做饭
    public List<String> cook(String... meauNames) {
        return Arrays.stream(meauNames).map(
                meauName -> {
                    /*try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/
                    return "美味佳肴" + meauName;
                }
        ).collect(toList());
    }

    //玩游戏升级等级
    public static void play(int interval) {
        for (int i = 0; i < interval; i++) {
            try {
                Thread.sleep(10);
                atomicInteger.getAndAdd(1);
                System.out.println(Thread.currentThread().getName() + ":成功升级+1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
