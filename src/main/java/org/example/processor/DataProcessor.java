package org.example.processor;

import org.example.config.AppConfig;
import org.example.enums.DataType;

import java.io.BufferedWriter;

public class DataProcessor {

    private final AppConfig appConfig;
    private final String outputFileName;
    private BufferedWriter writer;

    public DataProcessor(AppConfig appConfig, DataType dataType) {
        this.appConfig = appConfig;
        this.outputFileName = dataType.getFileName();
    }


}

