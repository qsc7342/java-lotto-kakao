package controller;

import domain.*;
import view.InputView;
import view.OutputView;
import view.WinningStatistics;
import utils.YieldCalculator;
import utils.LottoGenerator;
import utils.InputValidator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static constant.LottoSetting.LOTTO_PRICE;

public class LottoController {
    private static final String LOTTO_NUMBER_DELIMITER = ", ";

    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        Payment payment = new Payment(Integer.parseInt(inputView.getUserInputPayment()));
        int numberOfLotto = payment.getDivideByInt(LOTTO_PRICE);
        outputView.printNumberOfLotto(numberOfLotto);

        List<LottoNumbers> lottoNumbersList = LottoGenerator.generateLotto(numberOfLotto);
        String purchasedLotto = lottoNumbersList.stream()
                .map(LottoNumbers::toString)
                .collect(Collectors.joining("\n"));
        outputView.printLotto(purchasedLotto);

        execute(payment, lottoNumbersList);
    }

    public void execute(Payment payment, List<LottoNumbers> lottoNumbersList) {
        LottoNumbers winLottoNumbers = new LottoNumbers(Arrays.stream(inputView.getUserInputLottoNumbers().split(LOTTO_NUMBER_DELIMITER))
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoNumber::of)
                .collect(Collectors.toList()));

        LottoNumber bonusBall = LottoNumber.of(Integer.parseInt(inputView.getUserInputBonusBallNumbers()));

        Lotto lotto = new Lotto(lottoNumbersList);
        Map<Rank, Integer> rankMap = lotto.rankEachLotto(winLottoNumbers, bonusBall);

        outputView.printStatistics(new WinningStatistics(rankMap).toString());
        outputView.printYield(YieldCalculator.calculate(payment, rankMap));
    }

}
