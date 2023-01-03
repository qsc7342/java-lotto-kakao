package view;

public class OutputView {

    private static final String NUMBER_OF_LOTTO_MESSAGE = "%d개를 구매했습니다.";
    private static final String RESULT_STATISTICS_MESSAGE = "\n당첨 통계\n---------";
    private static final String YIELD_MESSAGE = "총 수익률은 %.2f입니다.";


    public void printNumberOfLotto(int num) {
        System.out.println(String.format(NUMBER_OF_LOTTO_MESSAGE, num));
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
