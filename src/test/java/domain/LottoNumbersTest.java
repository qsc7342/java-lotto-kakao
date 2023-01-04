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
                LottoNumber.of(31),
                LottoNumber.of(18),
                LottoNumber.of(5),
                LottoNumber.of(22),
                LottoNumber.of(41),
                LottoNumber.of(9)
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
        lottoNumberList.set(1, LottoNumber.of(31));

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
                LottoNumber.of(5),
                LottoNumber.of(9),
                LottoNumber.of(18),
                LottoNumber.of(22),
                LottoNumber.of(31),
                LottoNumber.of(41)
        ));

        // then
        assertThat(lottoNumbers).isEqualTo(sortedLottoNumbers);
    }

    @ParameterizedTest
    @MethodSource("provideLottoRank")
    void 로또_결과에_맞는_랭크를_반환한다(WinningLotto winningLotto, Rank expected) {
        // given
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberList);

        // when, then
        assertThat(winningLotto.getRank(lottoNumbers)).isEqualTo(Optional.ofNullable(expected));
    }

    private static Stream<Arguments> provideLottoRank() {
        LottoNumbers firstPlaceLottoNumbers = new LottoNumbers(List.of(
                LottoNumber.of(31),
                LottoNumber.of(18),
                LottoNumber.of(5),
                LottoNumber.of(22),
                LottoNumber.of(41),
                LottoNumber.of(9)
        ));

        LottoNumbers secondOrThirdPlaceLottoNumbers = new LottoNumbers(List.of(
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

        LottoNumber bonusBallForSecondPlace = LottoNumber.of(9);
        LottoNumber bonusBallNotAffect = LottoNumber.of(45);

        return Stream.of(
                Arguments.arguments(new WinningLotto(firstPlaceLottoNumbers, bonusBallNotAffect), Rank.FIRST_PLACE),
                Arguments.arguments(new WinningLotto(secondOrThirdPlaceLottoNumbers, bonusBallForSecondPlace), Rank.SECOND_PLACE),
                Arguments.arguments(new WinningLotto(secondOrThirdPlaceLottoNumbers, bonusBallNotAffect), Rank.THIRD_PLACE),
                Arguments.arguments(new WinningLotto(fourthPlaceLottoNumbers, bonusBallNotAffect), Rank.FOURTH_PLACE),
                Arguments.arguments(new WinningLotto(fifthPlaceLottoNumbers, bonusBallNotAffect), Rank.FIFTH_PLACE),
                Arguments.arguments(new WinningLotto(noPlaceLottoNumbers, bonusBallNotAffect), null)
        );
    }
}
