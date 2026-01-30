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
    private BufferedWriter bufferedWriter;
    private final Statistics statistics;

    public DataProcessor(AppConfig appConfig, DataType dataType) {
        this.appConfig = appConfig;
        this.outputFileName = appConfig.filePrefix() + dataType.getFileName();

        Statistics statistics = null;
        switch (appConfig.statisticsMode()) {
            case SHORT -> statistics = new ShortStatistic(dataType.getTypeName());
            case FULL -> {
                if (dataType == DataType.STRING) {
                    statistics = new FullStringStatistics(dataType.getTypeName());
                } else {
                    statistics = new FullNumericStatistics(dataType.getTypeName());
                }
            }
        }

        this.statistics = statistics;
    }

    public void process(String line) {
        try {
            // Lazy инициализация writer
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

            // Создаем директории, только если они указаны в пути и существуют
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

    public void addValueForStatistic(String line){
        statistics.addValue(line);
    }


    public void printStatistic(){
        statistics.printStatistic();
    }
}

