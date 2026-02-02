package org.example.statistic;

/**
 * Реализация полной статистики для строковых данных.
 * Собирает количество элементов, минимальную и максимальную длину строк.
 */
public class FullStringStatistics implements Statistics {

    private long count = 0;
    private int minLength = Integer.MAX_VALUE;
    private int maxLength = 0;

    /**
     * Добавляет значение в статистику.
     * Обновляет счетчик элементов и вычисляет минимальную/максимальную длину.
     *
     * @param value строковое значение для добавления
     */
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

    /**
     * Выводит полную статистику по строковым данным в консоль.
     * Если нет данных, метод ничего не выводит.
     */
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
