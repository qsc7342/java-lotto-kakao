package utils;

import domain.LottoNumbers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.LottoGenerator;

import java.util.List;
import java.util.stream.IntStream;

import static constant.LottoSetting.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {

    @Test
    @DisplayName("로또 제너레이터는 로또의 크기만큼의 숫자를 갖는 로또넘버를 생성한다.")
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
