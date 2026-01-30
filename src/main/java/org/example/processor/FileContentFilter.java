package org.example.processor;

import org.example.config.AppConfig;
import org.example.config.CommandLineParser;
import org.example.enums.DataType;

import java.util.HashMap;
import java.util.Map;


public class FileContentFilter {

    private final Map<DataType, DataProcessor> processors = new HashMap<>();

    public void run(String[] args){
        AppConfig appConfig = new CommandLineParser().parse(args);

    }

}
