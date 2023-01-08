package domain.lotto;

import domain.rank.Rank;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Lotto {

    private final List<LottoNumbers> lottoNumbersList;

    public Lotto(List<LottoNumbers> lottoNumbersList) {
        this.lottoNumbersList = lottoNumbersList;
    }

    public Map<Rank, Integer> rankEachLotto(WinningLotto winningLotto) {
        return lottoNumbersList.stream()
                .map(winningLotto::getRank)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.summingInt(e -> 1)));
    }

}
