package org.example.statistic;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Реализация полной статистики для числовых данных (целые и вещественные числа).
 * Собирает количество элементов, минимальное/максимальное значение, сумму и среднее.
 */
public class FullNumericStatistics implements Statistics{

    private final String typeName;
    private long count = 0;
    private BigDecimal min = null;
    private BigDecimal max = null;
    private BigDecimal sum = BigDecimal.ZERO;

    public FullNumericStatistics(String typeName) {
        this.typeName = typeName;
    }

    /**
     * Добавляет числовое значение в статистику.
     * Обновляет счетчик, минимальное/максимальное значение и сумму.
     *
     * @param value строковое представление числа для добавления
     */
    @Override
    public void addValue(String value) {
        BigDecimal number = new BigDecimal(value);
        if (min == null || number.compareTo(min) < 0) {
            min = number;
        }
        if (max == null || number.compareTo(max) > 0) {
            max = number;
        }
        sum = sum.add(number);
        count++;

    }

    /**
     * Выводит полную статистику по числовым данным в консоль.
     * Если нет данных, метод ничего не выводит.
     * Выводит количество элементов, минимальное/максимальное значение, сумму и среднее.
     */
    @Override
    public void printStatistic() {
        if(count == 0) {return;}

        BigDecimal average = sum.divide(BigDecimal.valueOf(count), 10, RoundingMode.HALF_UP);
        System.out.println(String.format(
                """
                        *** Полная статистика по %s ***
                        Количество элементов: %d
                        Минимальное значение: %s
                        Максимальное значение: %s
                        Сумма: %s
                        Среднее: %s
                        """,
                typeName, count, min.toPlainString(), max.toPlainString(), sum.toPlainString(), average.toPlainString()
        ));
    }

}
