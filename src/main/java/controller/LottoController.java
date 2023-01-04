package controller;

import domain.*;
import view.InputView;
import view.OutputView;
import view.PurchasedInfo;
import view.WinningStatistics;
import utils.YieldCalculator;
import utils.LottoGenerator;

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
        List<LottoNumbers> lottoNumbersList = purchaseLotto(payment);

        LottoNumbers winLottoNumbers = getWinLottoNumbersFromUser();
        LottoNumber bonusBall = getBonusBallFromUser();
        
        execute(payment, lottoNumbersList, new WinningLotto(winLottoNumbers, bonusBall));
    }

    private List<LottoNumbers> purchaseLotto(Payment payment) {
        int numberOfLotto = payment.getDivideByInt(LOTTO_PRICE);
        outputView.printNumberOfLotto(numberOfLotto);

        List<LottoNumbers> lottoNumbersList = LottoGenerator.generateLotto(numberOfLotto);
        outputView.printLotto(new PurchasedInfo().getPurchasedInfoString(lottoNumbersList));

        return lottoNumbersList;
    }

    private LottoNumber getBonusBallFromUser() {
        return LottoNumber.of(Integer.parseInt(inputView.getUserInputBonusBallNumbers()));
    }

    public void execute(Payment payment, List<LottoNumbers> lottoNumbersList, WinningLotto winningLotto) {
        Lotto lotto = new Lotto(lottoNumbersList);
        Map<Rank, Integer> rankMap = lotto.rankEachLotto(winningLotto);
        outputView.printStatistics(new WinningStatistics(rankMap).toString());

        outputView.printYield(YieldCalculator.calculate(payment, rankMap));
    }

    private LottoNumbers getWinLottoNumbersFromUser() {
        return new LottoNumbers(
                Arrays.stream(inputView.getUserInputLottoNumbers().split(LOTTO_NUMBER_DELIMITER))
                .map(LottoNumber::of)
                .collect(Collectors.toList()));
    }
}
