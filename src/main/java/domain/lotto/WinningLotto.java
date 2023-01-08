package domain.lotto;

import domain.rank.Rank;


public class WinningLotto {
    private static final int BONUS_BALL_FLAG = 5;

    private final LottoNumbers lottoNumbers;
    private final LottoNumber bonusBall;

    public WinningLotto(LottoNumbers lottoNumbers, LottoNumber bonusBall) {
        this.lottoNumbers = lottoNumbers;
        this.bonusBall = bonusBall;
    }

    public Rank getRank(LottoNumbers lottoNumbers) {
        int matchCount = this.lottoNumbers.getMatchedCount(lottoNumbers);
        if(isSecondPlace(lottoNumbers, matchCount)) {
            return Rank.SECOND_PLACE;
        }
        return Rank.findRank(matchCount);
    }

    private boolean isSecondPlace(LottoNumbers lottoNumbers, int matchCount) {
        return matchCount == BONUS_BALL_FLAG && lottoNumbers.contains(bonusBall);
    }
}
