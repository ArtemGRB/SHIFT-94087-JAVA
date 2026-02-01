package org.example.processor;

import org.example.config.AppConfig;
import org.example.config.CommandLineParser;
import org.example.enums.DataType;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileContentFilter {


    public void run(String[] args) {
        AppConfig appConfig = new CommandLineParser().parse(args);
        Map<DataType, DataProcessor> processors = ProcessorFactory.createProcessors(appConfig);
        try {
            processInputFiles(appConfig.inputFiles(), processors);
        } finally {
            closeProcessors(processors);
        }
    }

    private void processInputFiles(List<Path> inputFiles, Map<DataType, DataProcessor> processors) {
        for (Path inputFile : inputFiles) {
            try (var lines = Files.lines(inputFile)) {
                System.out.println("Обработка файла: " + inputFile);
                lines.forEach(line -> {
                    DataType type = CheckerTypeData.check(line);
                    processors.get(type).process(line);
                });
            } catch (IOException e) {
                System.err.println("Ошибка чтения файла '" + inputFile + "': " + e.getMessage() + ". Файл будет пропущен.");
            } catch (UncheckedIOException e) {
                System.err.println("Ошибка записи данных из файла '" + inputFile + "': " + e.getCause().getMessage() + ". Обработка остановлена.");
                throw e;
            }
        }
    }

    private void closeProcessors(Map<DataType, DataProcessor> processors) {
        processors.values().forEach(p -> {
            try {
                p.close();
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        });
    }
}
