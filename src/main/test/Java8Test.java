import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

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
        }).collect(Collectors.toList());
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
        System.out.println(checkApple(apple,new AppleWeightPredicate(),250.0));
        System.out.println(checkApple(apple,new AppleWeightPredicate(),5000.0));
    }

    public static <T> boolean checkApple(Apple apple, ApplePredicate applePredicate, T t) {
        return applePredicate.test(apple, t);
    }
}
