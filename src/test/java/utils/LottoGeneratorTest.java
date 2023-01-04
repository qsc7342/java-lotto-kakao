package utils;

import domain.lotto.LottoNumbers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static constant.LottoSetting.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {

    @Test
    void 로또_크기_만큼의_로또넘버를_생성한다() {
        // given
        int payment = 14000;

        // when
        List<LottoNumbers> lottoNumbers = LottoGenerator.generateLotto(payment / LOTTO_PRICE);

        assertThat(lottoNumbers.size()).isEqualTo(payment / LOTTO_PRICE);
    }
}
