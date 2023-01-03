package domain;

import java.util.Objects;

import static constant.ExceptionMessage.*;
import static constant.LottoSetting.*;

public class LottoNumber implements Comparable<LottoNumber> {

    private final int number;

    public LottoNumber(int number) {
        validateNumber(number);
        this.number = number;
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
