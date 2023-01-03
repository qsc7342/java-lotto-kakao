package utils;

import domain.Rank;
import org.junit.jupiter.api.Test;
import utils.YieldCalculator;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class YieldCalculatorTest {
    @Test
    void calculate() {
        YieldCalculator yieldCalculator = new YieldCalculator();
        Map<Rank, Integer> rankMap = Map.of(
                Rank.FIRST_PLACE, 0,
                Rank.SECOND_PLACE, 0,
                Rank.THIRD_PLACE, 0,
                Rank.FOURTH_PLACE, 0,
                Rank.FIFTH_PLACE, 1
        );
        assertThat(yieldCalculator.calculate(14000, rankMap)).isEqualTo(0.35);

    }
}