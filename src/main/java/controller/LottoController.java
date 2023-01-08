package controller;

import domain.lotto.Lotto;
import domain.lotto.LottoNumber;
import domain.lotto.LottoNumbers;
import domain.lotto.WinningLotto;
import domain.payment.Payment;
import domain.rank.Rank;
import utils.LottoStringUtils;
import utils.YieldCalculator;
import view.InputView;
import view.OutputView;
import view.PurchasedInfo;
import view.WinningStatistics;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static constant.LottoSetting.LOTTO_PRICE;
import static utils.LottoGenerator.*;
import static utils.LottoStringUtils.*;

public class LottoController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoController() {
        inputView = new InputView();
        outputView = new OutputView();
    }

    public void run() {
        Payment payment = new Payment(inputView.getUserInputPayment());
        List<LottoNumbers> lottoNumbersList = purchaseLotto(payment);

        LottoNumbers winLottoNumbers = getWinLottoNumbersFromUser();
        LottoNumber bonusBall = getBonusBallFromUser();

        Map<Rank, Integer> rankMap = executeLotto(lottoNumbersList, new WinningLotto(winLottoNumbers, bonusBall));

        finishLotto(payment, rankMap);
    }

    private List<LottoNumbers> purchaseLotto(Payment payment) {
        int numberOfManualLotto = Integer.parseInt(inputView.getUserInputManualLottoCount());
        int numberOfAutomaticLotto = payment.valueOfSubtractByInt(numberOfManualLotto * LOTTO_PRICE) / LOTTO_PRICE;
        outputView.printNumberOfLotto(numberOfManualLotto, numberOfAutomaticLotto);

        List<LottoNumbers> lottoNumbersList = purchaseManualLotto(numberOfManualLotto);
        lottoNumbersList.addAll(generateLotto(numberOfAutomaticLotto));

        outputView.printLotto(new PurchasedInfo().getPurchasedInfoString(lottoNumbersList));

        return lottoNumbersList;
    }

    private List<LottoNumbers> purchaseManualLotto(int count) {
        return inputView.getUserInputManualLottoNumbers(count)
                .stream()
                .map(LottoStringUtils::stringWithDelimiterToLottoNumbers)
                .collect(Collectors.toList());
    }

    private LottoNumbers getWinLottoNumbersFromUser() {
        return stringWithDelimiterToLottoNumbers(inputView.getUserInputLottoNumbers());
    }

    private LottoNumber getBonusBallFromUser() {
        return LottoNumber.of(inputView.getUserInputBonusBallNumbers());
    }

    public Map<Rank, Integer> executeLotto(List<LottoNumbers> lottoNumbersList, WinningLotto winningLotto) {
        Lotto lotto = new Lotto(lottoNumbersList);

        return lotto.rankEachLotto(winningLotto);
    }

    private void finishLotto(Payment payment, Map<Rank, Integer> rankMap) {
        outputView.printStatistics(new WinningStatistics(rankMap).toString());
        outputView.printYield(YieldCalculator.calculate(payment, rankMap));
    }
}
