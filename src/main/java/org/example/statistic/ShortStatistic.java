package org.example.statistic;

public class ShortStatistic implements Statistics {

    private final String typeName;
    private long count = 0;

    public ShortStatistic(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public <T> void addValue(T value) {
        count++;
    }

    @Override
    public void printStatistic() {
        System.out.println(String.format("--- Краткая статистика по '%s' ---\nКоличество элементов: %d", typeName, count));
    }
}
