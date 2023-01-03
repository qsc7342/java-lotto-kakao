package domain;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumbers;
import domain.Rank;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoTest {

    @ParameterizedTest
    @MethodSource("provideLotto")
    void 로또_당첨_등수를_계산한다(List<LottoNumbers> lottoNumbersList, LottoNumber bonusBall, List<Integer> expectedRankCountList) {
        // given
        Lotto lotto = new Lotto(lottoNumbersList);
        LottoNumbers winLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(22),
                new LottoNumber(41),
                new LottoNumber(9)
        ));

        // when
        Map<Rank, Integer> rankMap = lotto.rankEachLotto(winLottoNumbers, bonusBall);

        // then
        List<Integer> actualRankCountList = Arrays.stream(Rank.values())
                .map(rankMap::get)
                .collect(Collectors.toList());
        assertThat(actualRankCountList).isEqualTo(expectedRankCountList);
    }

    private static Stream<Arguments> provideLotto() {
        LottoNumbers firstPlaceLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(22),
                new LottoNumber(41),
                new LottoNumber(9)
        ));

        LottoNumbers secondorThirdPlaceLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(22),
                new LottoNumber(41),
                new LottoNumber(1)
        ));

        LottoNumbers fourthPlaceLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(22),
                new LottoNumber(2),
                new LottoNumber(1)
        ));

        LottoNumbers fifthPlaceLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(3),
                new LottoNumber(2),
                new LottoNumber(1)
        ));

        LottoNumbers noPlaceLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(5),
                new LottoNumber(6),
                new LottoNumber(4),
                new LottoNumber(3),
                new LottoNumber(2),
                new LottoNumber(1)
        ));

        List<LottoNumbers> lottoNumbersList = List.of(firstPlaceLottoNumbers, secondorThirdPlaceLottoNumbers, fourthPlaceLottoNumbers, fifthPlaceLottoNumbers, noPlaceLottoNumbers);

        return Stream.of(
                Arguments.arguments(lottoNumbersList, new LottoNumber(1), List.of(1, 1, 0, 1, 1)),
                Arguments.arguments(lottoNumbersList, new LottoNumber(33), List.of(1, 0, 1, 1, 1))
        );
    }


}
