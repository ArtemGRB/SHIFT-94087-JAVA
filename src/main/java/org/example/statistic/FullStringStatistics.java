package org.example.statistic;

public class FullStringStatistics implements Statistics {

    private long count = 0;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = 0;


    @Override
    public void addValue(String value) {
        int len = value.length();
        if (len < minLength) {
            minLength = len;
        }
        if (len > maxLength) {
            maxLength = len;
        }
        count++;
    }

    @Override
    public void printStatistic() {
        if(count == 0) {return;}

        System.out.println(String.format(
                """
                        *** Полная статистика по строкам ***
                        Количество элементов: %d
                        Самая короткая строка (длина): %d
                        Самая длинная строка (длина): %d
                        """,
                count, minLength, maxLength
        ));
    }
}
