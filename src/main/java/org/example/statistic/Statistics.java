package org.example.statistic;

public interface Statistics {

    <T> void addValue(T value);

    String getStatistic();
}
