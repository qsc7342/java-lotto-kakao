package domain.rank;

import java.util.Arrays;

public enum Rank {

    FIRST_PLACE(6, 2_000_000_000),
    SECOND_PLACE(5, 30_000_000),
    THIRD_PLACE(5, 1_500_000),
    FOURTH_PLACE(4, 50_000),
    FIFTH_PLACE(3, 5_000),
    NOTHING(0, 0),
    ;

    private final int matchCount;
    private final long prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public int getMatchCount() {
        return matchCount;
    }
    public long getPrize() {
        return prize;
    }

    public static Rank findRank(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .reduce((second, third) -> third)
                .orElse(NOTHING);
    }

}
