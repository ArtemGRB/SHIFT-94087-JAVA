package org.example.statistic;

/**
 * Реализация краткой статистики, которая собирает только количество элементов.
 * Подходит для всех типов данных.
 */
public class ShortStatistic implements Statistics {

    private final String typeName;
    private long count = 0;

    public ShortStatistic(String typeName) {
        this.typeName = typeName;

    }
    /**
     * Добавляет значение в статистику, увеличивая счетчик элементов.
     *
     * @param value строковое значение для добавления (не анализируется)
     */
    @Override
    public void addValue(String value) {
        count++;
    }

    /**
     * Выводит краткую статистику в консоль.
     * Если нет данных, метод ничего не выводит.
     */
    @Override
    public void printStatistic() {
        if(count == 0) {return;}

        System.out.println("""
                          
                          *** Краткая статистика по %s ***
                          Количество элементов: %d""".formatted(typeName, count));
    }
}
