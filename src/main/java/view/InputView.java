package view;

import validator.InputValidator;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {

    private static final String PAYMENT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String PURCHASE_MANUAL_LOTTO_MESSAGE = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_NUMBER_INPUT_MESSAGE = "수동으로 구매할 번호를 입력해 주세요.";
    private static final String LOTTO_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해주세요";
    private static final String BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";

    private final Scanner scanner;
    private final InputValidator inputValidator;

    public InputView() {
        scanner = new Scanner(System.in);
        inputValidator = new InputValidator();
    }

    public String getUserInputPayment() {
        System.out.println(PAYMENT_INPUT_MESSAGE);
        return inputValidator.validateIsNumeric(scanner.nextLine());
    }

    public String getUserInputLottoNumbers() {
        System.out.println(LOTTO_NUMBERS_INPUT_MESSAGE);
        return inputValidator.validateLottoNumbersInput(scanner.nextLine());
    }

    public String getUserInputBonusBallNumbers() {
        System.out.println(BONUS_BALL_INPUT_MESSAGE);
        return inputValidator.validateIsNumeric(scanner.nextLine());
    }

    public String getUserInputManualLottoCount() {
        System.out.println(PURCHASE_MANUAL_LOTTO_MESSAGE);
        return inputValidator.validateIsNumeric(scanner.nextLine());
    }

    public List<String> getUserInputManualLottoNumbers(int count) {
        System.out.println(MANUAL_LOTTO_NUMBER_INPUT_MESSAGE);
        return IntStream.range(0, count)
                .mapToObj(i -> inputValidator.validateLottoNumbersInput(scanner.nextLine()))
                .collect(Collectors.toList());
    }
}
