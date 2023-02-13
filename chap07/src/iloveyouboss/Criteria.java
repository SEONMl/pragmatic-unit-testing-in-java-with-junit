package iloveyouboss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Criteria implements Iterable<Criterion> {

    private List<Criterion> criteria = new ArrayList<>();

    public void add(Criterion criterion) {
        criteria.add(criterion);
    }

    @Override
    public Iterator<Criterion> iterator() {
        return criteria.iterator();
    }

    public int arithmeticMean() {
        return 0;
    }

    public double geometricMean(int[] numbers) {
        int totalProduct = Arrays.stream(numbers).reduce(1, (product, number) -> product * number);
        return Math.pow(totalProduct, 1.0 / numbers.length);
    }

    private Criteria soleNeed(Question question, int value, Weight weight) {
        Criteria criteria1 = new Criteria();
        criteria1.add(new Criterion(new Answer(question, value), weight));
        return criteria1;
    }
}