package domain;

import domain.LottoNumber;
import domain.LottoNumbers;
import domain.Rank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static constant.LottoSetting.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumbersTest {

    private List<LottoNumber> lottoNumberList;

    @BeforeEach
    void setUp() {
        lottoNumberList = Arrays.asList(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(22),
                new LottoNumber(41),
                new LottoNumber(9)
        );
    }

    @Test
    void 여섯_개의_숫자_중_중복된_숫자가_없을_경우_정상적으로_생성된다() {
        // when, then
        assertThatCode(() -> new LottoNumbers(lottoNumberList))
                .doesNotThrowAnyException();
    }

    @Test
    void 여섯_개의_숫자_중_중복된_숫자가_존재할_경우_예외가_발생한다() {
        // given
        lottoNumberList.set(1, new LottoNumber(31));

        // when, then
        assertThatThrownBy(() -> new LottoNumbers(lottoNumberList))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 로또_숫자_개수_검증() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberList);

        // when, then
        assertThat(lottoNumbers.hasSize(LOTTO_SIZE)).isTrue();
    }

    @Test
    void 로또_숫자열은_오름차순이어야_한다() {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberList);
        LottoNumbers sortedLottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(5),
                new LottoNumber(9),
                new LottoNumber(18),
                new LottoNumber(22),
                new LottoNumber(31),
                new LottoNumber(41)
        ));

        // then
        assertThat(lottoNumbers).isEqualTo(sortedLottoNumbers);
    }

    @ParameterizedTest
    @MethodSource("provideLottoRank")
    void 숫자가_3개_미만으로_일치하면_아무일도_일어나지_않는다(LottoNumbers winLottoNumbers, LottoNumber bonusBall, Rank expected) {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberList);

        // when, then
        assertThat(lottoNumbers.compareWithWinLottoNumbers(winLottoNumbers, bonusBall)).isEqualTo(Optional.ofNullable(expected));
    }

    private static Stream<Arguments> provideLottoRank() {
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

        return Stream.of(
                Arguments.arguments(firstPlaceLottoNumbers, new LottoNumber(45), Rank.FIRST_PLACE),
                Arguments.arguments(secondorThirdPlaceLottoNumbers, new LottoNumber(9), Rank.SECOND_PLACE),
                Arguments.arguments(secondorThirdPlaceLottoNumbers, new LottoNumber(45), Rank.THIRD_PLACE),
                Arguments.arguments(fourthPlaceLottoNumbers, new LottoNumber(45), Rank.FOURTH_PLACE),
                Arguments.arguments(fifthPlaceLottoNumbers, new LottoNumber(45), Rank.FIFTH_PLACE),
                Arguments.arguments(noPlaceLottoNumbers, new LottoNumber(45), null)
        );
    }
}
