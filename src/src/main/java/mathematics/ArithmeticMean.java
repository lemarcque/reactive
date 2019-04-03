package mathematics;

import java.util.List;

public class ArithmeticMean extends Mean {

    @Override
    public Double apply(List<Integer> integers) {
        int sum = integers
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        return (double) sum / integers.size();
    }
}
