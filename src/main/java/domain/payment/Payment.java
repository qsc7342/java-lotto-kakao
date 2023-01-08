package domain.payment;


import static constant.ExceptionMessage.Lotto.INVALID_LOTTO_PAYMENT_MESSAGE;

public class Payment {
    public static final int MIN_LOTTO_PAYMENT = 5000;
    private final int payment;

    public Payment(int payment) {
        validate(payment);
        this.payment = payment;
    }

    public Payment(String payment) {
        this(Integer.parseInt(payment));
    }

    public void validate(int payment) {
        if (payment < MIN_LOTTO_PAYMENT) {
            throw new RuntimeException(INVALID_LOTTO_PAYMENT_MESSAGE);
        }
    }

    public double valueOfDividedByDouble(double num) {
        return num / payment;
    }

    public int valueOfSubtractByInt(int num) {
        return payment - num;
    }
}
