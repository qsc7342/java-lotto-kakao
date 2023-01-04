package view;

import domain.lotto.LottoNumbers;

import java.util.List;
import java.util.stream.Collectors;

public class PurchasedInfo {
    public String getPurchasedInfoString(List<LottoNumbers> lottoNumbersList) {
        return lottoNumbersList.stream()
                .map(LottoNumbers::toString)
                .collect(Collectors.joining("\n"));
    }
}
