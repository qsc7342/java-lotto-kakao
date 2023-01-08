package utils;

import domain.lotto.LottoNumbers;
import domain.payment.Payment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static constant.LottoSetting.*;
import static org.assertj.core.api.Assertions.assertThat;

public class LottoGeneratorTest {

    @Test
    void 로또_크기_만큼의_로또넘버를_생성한다() {
        // given
        Payment payment = new Payment(14000);
        int count = payment.valueOfSubtractByInt(LOTTO_PRICE);
        // when
        List<LottoNumbers> lottoNumbers = LottoGenerator.generateLotto(count);

        assertThat(lottoNumbers.size()).isEqualTo(count);
    }
}
