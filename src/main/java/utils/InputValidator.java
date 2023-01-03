package utils;

import static constant.ExceptionMessage.INVALID_LOTTO_PAYMENT_MESSAGE;
import static constant.LottoSetting.MIN_LOTTO_PAYMENT;

public class InputValidator {

    public void validatePayment(int payment) {
        if (payment < MIN_LOTTO_PAYMENT) {
            throw new RuntimeException(INVALID_LOTTO_PAYMENT_MESSAGE);
        }
    }

}
