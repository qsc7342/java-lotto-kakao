import java.util.Objects;

import static constant.ExceptionMessage.*;

public class LottoNumber {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 45;

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
}
