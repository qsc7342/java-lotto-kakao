package controller;

import domain.Lotto;
import domain.LottoNumber;
import domain.LottoNumbers;
import domain.Rank;
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
    private final InputValidator inputValidator;

    public LottoController() {
        inputView = new InputView();
        outputView = new OutputView();
        inputValidator = new InputValidator();
    }

    public void run() {
        int payment = Integer.parseInt(inputView.getUserInputPayment());
        inputValidator.validatePayment(payment);
        outputView.printNumberOfLotto(payment / LOTTO_PRICE);

        List<LottoNumbers> lottoNumbersList = LottoGenerator.generateLotto(payment);
        String purchasedLotto = lottoNumbersList.stream()
                .map(LottoNumbers::toString)
                .collect(Collectors.joining("\n"));
        outputView.printLotto(purchasedLotto);

        execute(payment, lottoNumbersList);
    }

    public void execute(int payment, List<LottoNumbers> lottoNumbersList) {
        LottoNumbers winLottoNumbers = new LottoNumbers(Arrays.stream(inputView.getUserInputLottoNumbers().split(LOTTO_NUMBER_DELIMITER))
                .mapToInt(Integer::parseInt)
                .mapToObj(LottoNumber::new)
                .collect(Collectors.toList()));

        LottoNumber bonusBall = new LottoNumber(Integer.parseInt(inputView.getUserInputBonusBallNumbers()));

        Lotto lotto = new Lotto(lottoNumbersList);
        Map<Rank, Integer> rankMap = lotto.rankEachLotto(winLottoNumbers, bonusBall);

        outputView.printStatistics(new WinningStatistics(rankMap).toString());
        outputView.printYield(YieldCalculator.calculate(payment, rankMap));
    }

}
