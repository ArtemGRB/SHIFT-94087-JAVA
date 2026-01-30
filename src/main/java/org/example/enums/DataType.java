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
}