package domain.payment;

import static constant.ExceptionMessage.INVALID_LOTTO_PAYMENT_MESSAGE;
import static constant.LottoSetting.LOTTO_PRICE;

public class Payment {
    public static final int MIN_LOTTO_PAYMENT = 5000;
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
