package domain.lotto;

import domain.rank.Rank;

import java.util.Optional;

public class WinningLotto {
    private static final int BONUS_BALL_FLAG = 5;

    private LottoNumbers lottoNumbers;
    private LottoNumber bonusBall;

    public WinningLotto(LottoNumbers lottoNumbers, LottoNumber bonusBall) {
        this.lottoNumbers = lottoNumbers;
        this.bonusBall = bonusBall;
    }

    public Optional<Rank> getRank(LottoNumbers lottoNumbers) {
        int matchCount = this.lottoNumbers.getMatchedCount(lottoNumbers);
        if(isSecondPlace(lottoNumbers, matchCount)) {
            return Optional.of(Rank.SECOND_PLACE);
        }
        return Optional.ofNullable(Rank.findRank(matchCount));
    }

    private boolean isSecondPlace(LottoNumbers lottoNumbers, int matchCount) {
        return matchCount == BONUS_BALL_FLAG && lottoNumbers.contains(bonusBall);
    }
}
