package domain.lotto;

import domain.rank.Rank;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Lotto {

    private final List<LottoNumbers> lottoNumbersList;

    public Lotto(List<LottoNumbers> lottoNumbersList) {
        this.lottoNumbersList = lottoNumbersList;
    }

    public Map<Rank, Integer> rankEachLotto(WinningLotto winningLotto) {
        List<Rank> ranks = lottoNumbersList.stream()
                .map(winningLotto::getRank)
                .collect(Collectors.toList());

        return Arrays.stream(Rank.values())
                .collect(Collectors.toMap(rank -> rank, rank -> Collections.frequency(ranks, rank)));
    }

}
