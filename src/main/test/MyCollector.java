import com.google.common.collect.Lists;

import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author :sujinpeng
 * @Date :2018/7/12
 * @Time :19:36
 * @Description :我的收集器
 */
public class MyCollector<T> implements Collector {

    @Override
    public Supplier<List<T>> supplier() {
        return Lists::newArrayList;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Function<List<T>, List<T>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return null;
    }
}
