import io.netty.util.concurrent.CompleteFuture;

import java.util.concurrent.*;

/**
 * @author :huangjinyang
 * @Date :2018/7/27
 * @Time :14:26
 * @Description :线程学习测试
 */
public class ThreadTest extends BaseTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        for (int t = 1; t <= 10; t++) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(50, 50, 5, TimeUnit.SECONDS, new SynchronousQueue<>());
            CompletableFuture<Long> completableFuture = CompletableFuture.supplyAsync(() -> {
                long result = 0;
                for (int i = 1; i <= 10000000; i++) {
                    throw new RuntimeException("手动抛出异常");
                }
                return result;
            }, threadPoolExecutor).exceptionally(throwable -> 5L);
            CompletableFuture<Long> completableFuture1 = CompletableFuture.supplyAsync(() -> {
                long result = 0;
                for (int i = 1; i <= 100; i++) {
                    result += i;
                }
                return result;
            }, threadPoolExecutor);
            CompletableFuture result = CompletableFuture.anyOf(completableFuture, completableFuture1);
            System.out.println(result.isCompletedExceptionally() ? "异常" : result.get());

            //threadPoolExecutor.shutdownNow();
        }
    }

}
