package domain.lotto;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static constant.ExceptionMessage.Lotto.INVALID_LOTTO_NUMBER_MESSAGE;
import static constant.LottoSetting.*;

public class LottoNumber implements Comparable<LottoNumber> {
    private final int number;
    private static final Map<Integer, LottoNumber> lottoNumberCache = new HashMap<>();
    private LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
    }

    public static LottoNumber of(int number) {
        if (!lottoNumberCache.containsKey(number)) {
            lottoNumberCache.put(number, new LottoNumber(number));
        }
        return lottoNumberCache.get(number);
    }

    public static LottoNumber of(String number) {
        return of(Integer.parseInt(number));
    }

    private void validateNumber(int number) {
        if(number < MIN_VALUE || number > MAX_VALUE) {
            throw new RuntimeException(INVALID_LOTTO_NUMBER_MESSAGE);
        }
    }

    @Override
    public int compareTo(LottoNumber other) {
        return number - other.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNumber that = (LottoNumber) o;
        return number == that.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
