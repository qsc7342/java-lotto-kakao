package utils;

import domain.LottoNumber;
import domain.LottoNumbers;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.LottoSetting.*;

public class LottoGenerator {
    private static final List<Integer> LOTTO_NUMBER_LIST = IntStream
            .rangeClosed(MIN_VALUE, MAX_VALUE)
            .boxed()
            .collect(Collectors.toList());


    public static List<LottoNumbers> generateLotto(int payment) {
        return IntStream.range(0, payment / LOTTO_PRICE)
                .mapToObj(i -> generate())
                .collect(Collectors.toList());
    }

    private static LottoNumbers generate() {
        Collections.shuffle(LOTTO_NUMBER_LIST);

        return new LottoNumbers(
                LOTTO_NUMBER_LIST.subList(0, LOTTO_SIZE)
                        .stream()
                        .map(LottoNumber::new)
                        .collect(Collectors.toList())
        );
    }
}
