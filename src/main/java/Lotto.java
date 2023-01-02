import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static constant.LottoSetting.LOTTO_PRICE;

public class Lotto {

    private final Payment payment;
    private List<LottoNumbers> lottoNumbersList;

    public Lotto(int payment) {
        this.payment = new Payment(payment);
        this.lottoNumbersList = createLottoNumbersForPayment(payment);
    }

    private List<LottoNumbers> createLottoNumbersForPayment(int payment) {
        return IntStream.range(0, payment / LOTTO_PRICE)
                .mapToObj(i -> LottoGenerator.generate())
                .collect(Collectors.toList());
    }

}
