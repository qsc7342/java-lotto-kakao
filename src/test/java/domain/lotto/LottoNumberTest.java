package domain.lotto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {24, 33, 41, 5})
    void 올바른_범위의_로또넘버는_생성_가능하다(int number) {
        assertThatCode(() -> LottoNumber.of(number))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void 범위_밖의_로또넘버는_생성_불가능하다(int number) {
        assertThatThrownBy(() -> LottoNumber.of(number))
                .isInstanceOf(RuntimeException.class);
    }
}
