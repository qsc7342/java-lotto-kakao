package domain.lotto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static constant.ExceptionMessage.Lotto.NOT_UNIQUE_LOTTO_NUMBER_MESSAGE;
import static constant.LottoSetting.*;

public class LottoNumbers {
    private final List<LottoNumber> lottoNumbers;

    public LottoNumbers(List<LottoNumber> lottoNumbers) {
        validateLottoNumbers(lottoNumbers);
        this.lottoNumbers = lottoNumbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }
    
    public int getMatchedCount(LottoNumbers lottoNumbers) {
        return this.lottoNumbers.stream()
                .filter(lottoNumbers.lottoNumbers::contains)
                .collect(Collectors.toList())
                .size();
    }

    public boolean contains(LottoNumber bonusBall) {
        return lottoNumbers.contains(bonusBall);
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
