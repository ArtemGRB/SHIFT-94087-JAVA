package org.example.statistic;

public class FullStringStatistics implements Statistics{

    private final String typeName;
    private long count = 0;

    public FullStringStatistics(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public <T> void addValue(T value) {

    }

    @Override
    public void printStatistic() {
    }
}
