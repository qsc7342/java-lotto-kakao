package domain;

import java.util.Arrays;

public enum Rank {

    FIRST_PLACE(6, 2_000_000_000),
    SECOND_PLACE(-1, 30_000_000),
    THIRD_PLACE(5, 1_500_000),
    FOURTH_PLACE(4, 50_000),
    FIFTH_PLACE(3, 5_000),
    ;

    private int matchCount;
    private long prize;

    Rank(int matchCount, int prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount == SECOND_PLACE.matchCount ? THIRD_PLACE.matchCount : matchCount;
    }

    public static Rank findRank(int matchCount) {
        return Arrays.stream(values())
                .filter(rank -> rank.matchCount == matchCount)
                .findAny()
                .orElse(null);
    }

}
