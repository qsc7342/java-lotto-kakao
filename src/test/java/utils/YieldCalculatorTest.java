package utils;

import domain.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class YieldCalculatorTest {
    @Test
    @DisplayName("총상금 / 지불액의 비율을 소수점 둘째자리 까지 반환한다.")
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