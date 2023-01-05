package utils;

import domain.payment.Payment;
import domain.rank.Rank;

import java.util.Map;

public class YieldCalculator {

    public static double calculate(Payment payment, Map<Rank, Integer> rankMap) {
        long sum = rankMap.keySet()
                .stream()
                .mapToLong(rank -> rank.getPrize() * rankMap.get(rank))
                .sum();

        return Math.floor(payment.valueOfDividedByDouble(sum) * 100) / 100;
    }
}
