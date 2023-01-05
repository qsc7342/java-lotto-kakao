package constant;

public class ExceptionMessage {
    public static class Lotto {
        public static final String INVALID_LOTTO_NUMBER_MESSAGE = "로또 번호는 1 ~ 45 사이여야 합니다.";
        public static final String NOT_UNIQUE_LOTTO_NUMBER_MESSAGE = "로또 번호는 중복되지 않아야 합니다.";
        public static final String INVALID_LOTTO_PAYMENT_MESSAGE = "최소 로또 구입 가격은 5000원 입니다.";
    }

    public static class Input {
        public static final String INVALID_PAYMENT_INPUT = "입력 금액은 0 이상의 숫자여야 합니다.";
        public static final String INVALID_LOTTO_NUMBERS_INPUT = "로또 번호 입력 형태가 올바르지 않습니다.";

    }
}
