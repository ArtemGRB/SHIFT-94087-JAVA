package org.example.statistic;

import org.example.config.AppConfig;
import org.example.enums.DataType;

public class ShortStatistic implements Statistics {

    private final String typeName;
    private long count = 0;

    public ShortStatistic(String typeName) {
        this.typeName = typeName;

    }

    @Override
    public void addValue(String value) {
        count++;
    }

    @Override
    public void printStatistic() {
        if(count == 0) {return;}

        System.out.println(String.format("\n*** Краткая статистика по %s ***\nКоличество элементов: %d", typeName, count));
    }
}
