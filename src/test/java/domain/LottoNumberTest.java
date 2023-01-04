package domain;

import domain.LottoNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class LottoNumberTest {
    @ParameterizedTest
    @DisplayName("1 ~ 45번 사이의 값을 갖는 로또넘버는 생성할 수 있다.")
    @ValueSource(ints = {24, 33, 41, 5})
    void validLottoNumber(int number) {
        assertThatCode(() -> new LottoNumber(number))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @DisplayName("범위 밖의 값을 갖는 로또넘버는 생성할 수 없다.")
    @ValueSource(ints = {0, 46})
    void invalidLottoNumber(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(RuntimeException.class);
    }
}
