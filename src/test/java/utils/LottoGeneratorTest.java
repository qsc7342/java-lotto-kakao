package utils;

import domain.LottoNumbers;
import org.junit.jupiter.api.Test;
import utils.LottoGenerator;

import java.util.List;
import java.util.stream.IntStream;

import static constant.LottoSetting.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {

    @Test
    void generate() {
        // given
        int payment = 14000;

        // when
        List<LottoNumbers> lottoNumbers = LottoGenerator.generateLotto(payment);

        // then
        IntStream.range(0, payment / LOTTO_PRICE)
                .forEach(idx -> assertThat(lottoNumbers.get(idx).hasSize(LOTTO_SIZE)).isTrue());
    }
}
