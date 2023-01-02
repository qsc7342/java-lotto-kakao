import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
    void 여섯_개의_숫자_중_중복된_숫자가_존재할_경우_예외가_발생한 () {
        // given
        lottoNumberList.set(1, new LottoNumber(31));

        // when, then
        assertThatThrownBy(() -> new LottoNumbers(lottoNumberList))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void 로또_숫자_개수_검증() {
        // given
        int expectedSize = 6;
        LottoNumbers lottoNumbers = new LottoNumbers(lottoNumberList);

        // when, then
        assertThat(lottoNumbers.hasSize(expectedSize)).isTrue();
    }
}
