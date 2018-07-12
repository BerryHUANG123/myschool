import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import us.codecraft.webmagic.selector.Json;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toList;

public class Java8Test extends BaseTest {

    private List<String> stringList;

    @Before
    public void init() {
        stringList = Lists.newArrayList();
        stringList.add("1a");
        stringList.add("2a");
        stringList.add("3a");
        stringList.add("4a");
        stringList.add("5a");
        stringList.add("6a");
    }

    @Test
    public void test() {
        List<String> list = stringList.parallelStream().filter(s -> {
            return StringUtils.contains(s, "5");
        }).collect(toList());
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testDefault() {
        String defaultStr = new DefaultTest() {
        }.getStr();
        System.out.println(defaultStr);
    }

    @Test
    public void testPredicate() {
        Apple apple = new Apple();
        apple.setWeight(500);
        System.out.println(checkApple(apple, new AppleWeightPredicate(), 250.0));
        System.out.println(checkApple(apple, new AppleWeightPredicate(), 5000.0));
    }

    public static <T> boolean checkApple(Apple apple, ApplePredicate applePredicate, T t) {
        return applePredicate.test(apple, t);
    }

    @Test
    public void testLambda() {
        Apple realApple = new Apple();
        realApple.setWeight(500);
        ApplePredicate<Double> applePredicate = (apple, standWeight) -> apple.getWeight() > standWeight;
        System.out.println(applePredicate.test(realApple, 300.0));
    }

    @Test
    public void test1() {
        Predicate<String> p = StringUtils::isNotBlank;
    }

    public Callable<String> fetch() {
        return () -> "1111";
    }

    @Test
    public void test2() {
        int[] intArr = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.stream(intArr).map(operand -> operand * operand).boxed().collect(toList()));
    }

    @Test
    public void test3() {
        int[] intArr = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.stream(intArr).filter(value -> value == 6).findAny().orElse(0));
    }

    @Test
    public void test4() {
        int[] intArr = new int[]{};
        System.out.println(Arrays.stream(intArr).min().orElse(-1));
    }

    @Test
    public void test5() {
        Integer[] intArr = new Integer[]{1, 2, null, 3, 5};
        int max;
        Arrays.stream(intArr).filter(Objects::nonNull).mapToInt(value -> value).max().orElseGet(() -> 0);
    }

    @Test
    public void test6() {
        Stream<int[]> pythagoreanTriples =
                IntStream.rangeClosed(1, 100).boxed()
                        .flatMap(a ->
                                IntStream.rangeClosed(a, 100)
                                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                        .mapToObj(b ->
                                                new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                        );
    }

    @Test
    public void test7() {
        long a = Stream.iterate(2, n -> n * n).collect(counting());
        System.out.println(a);
    }

}
