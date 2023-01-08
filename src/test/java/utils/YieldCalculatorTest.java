package utils;

import domain.payment.Payment;
import domain.rank.Rank;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class YieldCalculatorTest {
    @Test
    void 수익률을_계산하여_반환한다() {
        Map<Rank, Integer> rankMap = Map.of(
                Rank.FIRST_PLACE, 0,
                Rank.SECOND_PLACE, 0,
                Rank.THIRD_PLACE, 0,
                Rank.FOURTH_PLACE, 0,
                Rank.FIFTH_PLACE, 1
        );
        assertThat(YieldCalculator.calculate(new Payment(14000), rankMap)).isEqualTo(0.35);

    }
}