package validator;

import static constant.ExceptionMessage.Input.INVALID_LOTTO_NUMBERS_INPUT;
import static constant.ExceptionMessage.Input.INVALID_PAYMENT_INPUT;

public class InputValidator {
    private static final String isNumericRegex = "^[0-9]+$";
    private static final String LottoNumbersRegex = "^\\d{1,2}, \\d{1,2}, \\d{1,2}, \\d{1,2}, \\d{1,2}, \\d{1,2}$";
    public String validateIsNumeric(String input) {
        if(!input.matches(isNumericRegex)) {
            throw new RuntimeException(INVALID_PAYMENT_INPUT);
        }
        return input;
    }

    public String validateLottoNumbersInput(String input) {
        if(!input.matches(LottoNumbersRegex)) {
            throw new RuntimeException(INVALID_LOTTO_NUMBERS_INPUT);
        }
        return input;
    }

}
