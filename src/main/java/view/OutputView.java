package view;

public class OutputView {

    private static final String NUMBER_OF_LOTTO_MESSAGE = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String RESULT_STATISTICS_MESSAGE = "\n당첨 통계\n---------";
    private static final String YIELD_MESSAGE = "총 수익률은 %.2f입니다.";


    public void printNumberOfLotto(int manual, int auto) {
        System.out.printf((NUMBER_OF_LOTTO_MESSAGE) + "%n", manual, auto);
    }

    public void printLotto(String str) {
        System.out.println(str + "\n");
    }

    public void printStatistics(String str) {
        System.out.println(RESULT_STATISTICS_MESSAGE);
        System.out.println(str);
    }

    public void printYield(double yield) {
        System.out.println(String.format(YIELD_MESSAGE, yield));
    }

}
