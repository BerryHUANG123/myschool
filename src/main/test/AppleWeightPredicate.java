/**
 * @author :sujinpeng
 * @Date :2018/7/10
 * @Time :16:59
 * @Description :
 */
public class AppleWeightPredicate implements ApplePredicate<Double> {

    @Override
    public boolean test(Apple apple, Double weight) {
        if (weight != null && apple.getWeight() > weight) {
            return true;
        }
        return false;
    }
}
