package utils;

import domain.lotto.LottoNumber;
import domain.lotto.LottoNumbers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LottoStringUtils {
    private static final String LOTTO_NUMBER_DELIMITER = ", ";

    public static LottoNumbers stringWithDelimiterToLottoNumbers(String input) {
        return new LottoNumbers(
                Arrays.stream(input.split(LOTTO_NUMBER_DELIMITER))
                        .map(LottoNumber::of)
                        .collect(Collectors.toList())
        );
    }
}
