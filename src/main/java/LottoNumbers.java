import java.util.List;

import static constant.ExceptionMessage.NOT_UNIQUE_LOTTO_NUMBER_MESSAGE;

public class LottoNumbers {
    private static final int VALID_LOTTO_NUMBER_COUNT = 6;

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

    private void validateLottoNumbers(List<LottoNumber> lottoNumbers) {
        long numberCount = lottoNumbers.stream()
                .unordered()
                .distinct()
                .count();

        if (numberCount != VALID_LOTTO_NUMBER_COUNT) {
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
}
