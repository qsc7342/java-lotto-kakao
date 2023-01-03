package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static constant.ExceptionMessage.NOT_UNIQUE_LOTTO_NUMBER_MESSAGE;
import static constant.LottoSetting.*;

public class LottoNumbers {

    private static final int BONUS_BALL_FLAG = 5;

    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    public boolean hasSize(int size) {
        return lottoNumbers.size() == size;
    }

    public Optional<Rank> compareWithWinLottoNumbers(LottoNumbers winLottoNumbers, LottoNumber bonusBall) {
        List<LottoNumber> matchedLottoNumbers = new ArrayList<>(lottoNumbers);
        matchedLottoNumbers.retainAll(winLottoNumbers.lottoNumbers);

        int matchCount = matchedLottoNumbers.size();
        if(matchCount == BONUS_BALL_FLAG && lottoNumbers.contains(bonusBall)) {
            return Optional.of(Rank.SECOND_PLACE);
        }

        return Optional.ofNullable(Rank.findRank(matchCount));
    }

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        long numberCount = lottoNumbers.stream()
                .unordered()
                .distinct()
                .count();

        if (numberCount != LOTTO_SIZE) {
            throw new RuntimeException(NOT_UNIQUE_LOTTO_NUMBER_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumbers that = (LottoNumbers) o;
        return Objects.equals(lottoNumbers, that.lottoNumbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lottoNumbers);
    }

    @Override
    public String toString() {
        return '[' + lottoNumbers.stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(", ")) + ']';
    }
}
