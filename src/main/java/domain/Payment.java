package domain;

import static constant.ExceptionMessage.INVALID_LOTTO_PAYMENT_MESSAGE;
import static constant.LottoSetting.LOTTO_PRICE;
import static constant.LottoSetting.MIN_LOTTO_PAYMENT;

public class Payment {
    private final int payment;

    public Payment(int payment) {
        validate(payment);
        this.payment = payment;
    }

    public void validate(int payment) {
        if (payment < MIN_LOTTO_PAYMENT) {
            throw new RuntimeException(INVALID_LOTTO_PAYMENT_MESSAGE);
        }
    }

    public int getDivideByInt(int num) {
        return payment / LOTTO_PRICE;
    }

    public double getDividedByDouble(double num) {
        return num / payment;
    }
}
