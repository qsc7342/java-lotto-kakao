import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class LottoNumbersTest {

    @Test
    void 로또_숫자_개수_검증() {
        // given
        int expectedSize = 6;
        LottoNumbers lottoNumbers = new LottoNumbers(List.of(
                new LottoNumber(31),
                new LottoNumber(18),
                new LottoNumber(5),
                new LottoNumber(22),
                new LottoNumber(41),
                new LottoNumber(9)
        ));

        // when, then
        assertThat(lottoNumbers.hasSize(expectedSize)).isTrue();
    }

}
