package org.example.enums;

public enum DataType {
    INTEGER, FLOAT, STRING;

    public String getFileName() {
        return switch (this) {
            case INTEGER -> "integers.txt";
            case FLOAT -> "floats.txt";
            case STRING -> "strings.txt";
        };
    }

    public String getTypeName() {
        return switch (this) {
            case INTEGER -> "целые числа";
            case FLOAT -> "вещественные числа";
            case STRING -> "строки";
        };
    }
}