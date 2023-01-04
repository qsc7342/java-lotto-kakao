package utils;

import domain.lotto.LottoNumber;
import domain.lotto.LottoNumbers;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LottoStringUtilsTest {
    @Test
    void 입력값에_맞는_로또넘버를_생성해야_한다() {
        LottoNumbers actual = LottoStringUtils.stringWithDelimiterToLottoNumbers("1, 2, 3, 4, 5, 6");
        LottoNumbers expected = new LottoNumbers(List.of(
                LottoNumber.of(1),
                LottoNumber.of(2),
                LottoNumber.of(3),
                LottoNumber.of(4),
                LottoNumber.of(5),
                LottoNumber.of(6)
        ));
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
