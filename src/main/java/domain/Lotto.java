package domain;

import java.util.*;
import java.util.stream.Collectors;

public class Lotto {

    private final List<LottoNumbers> lottoNumbersList;

    public Lotto(List<LottoNumbers> lottoNumbersList) {
        this.lottoNumbersList = lottoNumbersList;
    }

    public Map<Rank, Integer> rankEachLotto(LottoNumbers winLottoNumbers, LottoNumber bonusBall) {
        List<Rank> ranks = lottoNumbersList.stream()
                .map(lottoNumbers -> lottoNumbers.compareWithWinLottoNumbers(winLottoNumbers, bonusBall))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        return Arrays.stream(Rank.values())
                        .collect(Collectors.toMap(rank -> rank, rank -> Collections.frequency(ranks, rank)));
    }

}
