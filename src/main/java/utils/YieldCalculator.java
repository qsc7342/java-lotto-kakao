package utils;

import domain.Payment;
import domain.Rank;

import java.util.Map;

public class YieldCalculator {

    public static double calculate(Payment payment, Map<Rank, Integer> rankMap) {
        long sum = rankMap.keySet()
                .stream()
                .mapToLong(rank -> rank.getPrize() * rankMap.get(rank))
                .sum();

        return Math.floor(payment.getDividedByDouble(sum) * 100) / 100;
    }
}
