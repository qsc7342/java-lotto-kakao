package utils;

import domain.Rank;

import java.util.Map;

public class YieldCalculator {

    public static double calculate(int payment, Map<Rank, Integer> rankMap) {
        long sum = rankMap.keySet()
                .stream()
                .mapToLong(rank -> rank.getPrize() * rankMap.get(rank))
                .sum();

        return Math.floor(((double) sum / payment) * 100) / 100;
    }
}
