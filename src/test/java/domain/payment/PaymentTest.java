package domain.payment;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

public class PaymentTest {
    @ParameterizedTest
    @ValueSource(ints = { 14000, 7500, 23412, 5000 })
    void 로또의_최소_구입_가격은_오천_원_이상이다(int payment) {
        // when, then
        assertThatCode(() -> new Payment(payment))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @ValueSource(ints = { 4999, 3835, 2125, 1082 })
    void 금액이_오천_원_미만일_경우_예외가_발생한다(int payment) {
        // when, then
        assertThatThrownBy(() -> new Payment(payment))
                .isInstanceOf(RuntimeException.class);
    }

}
