import org.junit.jupiter.api.Test;

import static constant.LottoSetting.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {
    @Test
    void generate() {
        // given
        LottoGenerator lottoGenerator = new LottoGenerator();

        // when
        LottoNumbers lottoNumbers = lottoGenerator.generate();

        // then
        assertThat(lottoNumbers.hasSize(LOTTO_SIZE)).isEqualTo(true);
    }
}
