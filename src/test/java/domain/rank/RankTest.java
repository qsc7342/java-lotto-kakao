package domain.rank;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class RankTest {
    @ParameterizedTest
    @CsvSource(value = {"6:FIRST_PLACE", "5:THIRD_PLACE", "4:FOURTH_PLACE", "3:FIFTH_PLACE", "2:NOTHING"}, delimiter = ':')
    void 일치_갯수에_맞는_랭크를_반환한다(int matchCount, Rank rank) {
        Assertions.assertThat(Rank.findRank(matchCount)).isEqualTo(rank);
    }
}
