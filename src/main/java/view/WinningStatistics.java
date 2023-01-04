package view;

import domain.Rank;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WinningStatistics {

    private static final String STATISTIC_FORMAT = "%d개 일치 (%d원) - %d개";
    private static final String SECOND_PLACE_STATISTIC_FORMAT = "%d개, 보너스 볼 일치 (%d원) - %d개";

    private final Map<Rank, Integer> rankMap;

    public WinningStatistics(Map<Rank, Integer> rankMap) {
        this.rankMap = rankMap;
    }

    @Override
    public String toString() {
        List<Rank> ranks = Arrays.stream(Rank.values())
                .collect(Collectors.toList());
        Collections.reverse(ranks);

        return ranks.stream()
                .map(this::getStatisticFormat)
                .collect(Collectors.joining("\n"));
    }

    private String getStatisticFormat(Rank rank) {
        return rank == Rank.SECOND_PLACE
                ? String.format(SECOND_PLACE_STATISTIC_FORMAT, rank.getMatchCount(), rank.getPrize(), rankMap.get(rank))
                : String.format(STATISTIC_FORMAT, rank.getMatchCount(), rank.getPrize(), rankMap.get(rank));
    }

}
