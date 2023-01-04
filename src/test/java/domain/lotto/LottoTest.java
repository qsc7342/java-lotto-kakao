package domain.lotto;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.LottoNumbers;
import domain.lotto.WinningLotto;
import domain.rank.Rank;
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
                LottoNumber.of(31),
                LottoNumber.of(18),
                LottoNumber.of(5),
                LottoNumber.of(22),
                LottoNumber.of(41),
                LottoNumber.of(9)
        ));

        // when
        Map<Rank, Integer> rankMap = lotto.rankEachLotto(new WinningLotto(winLottoNumbers, bonusBall));

        // then
        List<Integer> actualRankCountList = Arrays.stream(Rank.values())
                .map(rankMap::get)
                .collect(Collectors.toList());
        assertThat(actualRankCountList).isEqualTo(expectedRankCountList);
    }

    private static Stream<Arguments> provideLotto() {
        LottoNumbers firstPlaceLottoNumbers = new LottoNumbers(List.of(
                LottoNumber.of(31),
                LottoNumber.of(18),
                LottoNumber.of(5),
                LottoNumber.of(22),
                LottoNumber.of(41),
                LottoNumber.of(9)
        ));

        LottoNumbers secondorThirdPlaceLottoNumbers = new LottoNumbers(List.of(
                LottoNumber.of(31),
                LottoNumber.of(18),
                LottoNumber.of(5),
                LottoNumber.of(22),
                LottoNumber.of(41),
                LottoNumber.of(1)
        ));

        LottoNumbers fourthPlaceLottoNumbers = new LottoNumbers(List.of(
                LottoNumber.of(31),
                LottoNumber.of(18),
                LottoNumber.of(5),
                LottoNumber.of(22),
                LottoNumber.of(2),
                LottoNumber.of(1)
        ));

        LottoNumbers fifthPlaceLottoNumbers = new LottoNumbers(List.of(
                LottoNumber.of(31),
                LottoNumber.of(18),
                LottoNumber.of(5),
                LottoNumber.of(3),
                LottoNumber.of(2),
                LottoNumber.of(1)
        ));

        LottoNumbers noPlaceLottoNumbers = new LottoNumbers(List.of(
                LottoNumber.of(5),
                LottoNumber.of(6),
                LottoNumber.of(4),
                LottoNumber.of(3),
                LottoNumber.of(2),
                LottoNumber.of(1)
        ));

        List<LottoNumbers> lottoNumbersList = List.of(firstPlaceLottoNumbers, secondorThirdPlaceLottoNumbers, fourthPlaceLottoNumbers, fifthPlaceLottoNumbers, noPlaceLottoNumbers);

        return Stream.of(
                Arguments.arguments(lottoNumbersList, LottoNumber.of(1), List.of(1, 1, 0, 1, 1)),
                Arguments.arguments(lottoNumbersList, LottoNumber.of(33), List.of(1, 0, 1, 1, 1))
        );
    }


}
