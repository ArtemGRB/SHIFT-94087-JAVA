package org.example.processor;

import org.example.config.AppConfig;
import org.example.enums.DataType;
import org.example.enums.StatisticsMode;
import org.example.statistic.FullNumericStatistics;
import org.example.statistic.FullStringStatistics;
import org.example.statistic.ShortStatistic;
import org.example.statistic.Statistics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataProcessor {

    private final AppConfig appConfig;
    private final String outputFileName;
    private Statistics statistics;
    private BufferedWriter bufferedWriter;

    public DataProcessor(AppConfig appConfig, DataType dataType) {
        this.appConfig = appConfig;

        if (appConfig.statisticsMode() == StatisticsMode.FULL) {
            switch (dataType) {
                case FLOAT -> this.statistics = new FullNumericStatistics("вещественным числам");
                case INTEGER -> this.statistics = new FullNumericStatistics("целым числам");
                case STRING -> this.statistics = new FullStringStatistics();
            }
        } else {
            switch (dataType) {
                case FLOAT -> this.statistics = new ShortStatistic("вещественным числам");
                case INTEGER -> this.statistics = new ShortStatistic("целым числам");
                case STRING -> this.statistics = new ShortStatistic("строкам");
            }
        }
            this.outputFileName = appConfig.filePrefix() + dataType.getFileName();
    }

    public void process(String line) {
        try {
            initializationBufferedWriter();
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } catch (IOException e) {
            // Превращаем проверяемое исключение в непроверяемое, чтобы прервать stream
            throw new UncheckedIOException("Ошибка записи в файл '" + outputFileName + "'", e);
        }
    }

    private void initializationBufferedWriter() throws IOException {
        if (bufferedWriter == null) {
            Path outputPath = appConfig.outputPath().resolve(outputFileName);
            Path parentDir = outputPath.getParent();

            if (parentDir != null) {
                Files.createDirectories(parentDir);
            }

            bufferedWriter = new BufferedWriter(new FileWriter(outputPath.toFile(), appConfig.appendMode()));
        }
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void close() throws IOException {
        if (bufferedWriter != null) {
            bufferedWriter.close();
        }
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public AppConfig getAppConfig() {
        return appConfig;
    }
}

