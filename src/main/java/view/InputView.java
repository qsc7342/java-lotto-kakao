package view;

import java.util.Scanner;

public class InputView {

    private static final String PAYMENT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTO_NUMBERS_INPUT_MESSAGE = "지난 주 당첨 번호를 입력해주세요";
    private static final String BONUS_BALL_INPUT_MESSAGE = "보너스 볼을 입력해 주세요.";

    private Scanner scanner;

    public InputView() {
        scanner = new Scanner(System.in);
    }

    public String getUserInputPayment() {
        System.out.println(PAYMENT_INPUT_MESSAGE);
        return scanner.nextLine();
    }

    public String getUserInputLottoNumbers() {
        System.out.println(LOTTO_NUMBERS_INPUT_MESSAGE);
        return scanner.nextLine();
    }

    public String getUserInputBonusBallNumbers() {
        System.out.println(BONUS_BALL_INPUT_MESSAGE);
        return scanner.nextLine();
    }

}
