package org.example.processor;

import org.example.config.AppConfig;
import org.example.config.CommandLineParser;
import org.example.enums.DataType;
import org.example.enums.StatisticsMode;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

/**
 * Основной класс приложения для фильтрации содержимого файлов по типам данных.
 * Координирует работу парсера аргументов, процессоров данных и сбора статистики.
 */
public class FileContentFilter {

    /**
     * Запускает процесс фильтрации файлов на основе аргументов командной строки.
     *
     * @param args аргументы командной строки
     */
    public void run(String[] args) {
        AppConfig appConfig = new CommandLineParser().parse(args);
        Map<DataType, DataProcessor> processors = ProcessorFactory.createProcessors(appConfig);
        try {
            processInputFiles(appConfig.inputFiles(), processors);

            if (appConfig.statisticsMode() != StatisticsMode.NONE) {
                printStatistic(processors);
            }
        } finally {
            closeProcessors(processors);
        }
    }

    /**
     * Обрабатывает все входные файлы, распределяя строки по соответствующим процессорам.
     *
     * @param inputFiles список путей к входным файлам
     * @param processors карта процессоров по типам данных
     */
    private void processInputFiles(List<Path> inputFiles, Map<DataType, DataProcessor> processors) {
        for (Path inputFile : inputFiles) {
            try (var lines = Files.lines(inputFile)) {
                System.out.println("Обработка файла: " + inputFile);
                lines.forEach(line -> {
                    DataType type = CheckerTypeData.check(line);

                    DataProcessor processor = processors.get(type);
                    processor.process(line);
                    if (processor.getAppConfig().statisticsMode() != StatisticsMode.NONE) {
                        processor.getStatistics().addValue(line);
                    }
                });
            } catch (IOException e) {
                System.err.println("Ошибка чтения файла '" + inputFile + "': " + e.getMessage() + ". Файл будет пропущен.");
            } catch (UncheckedIOException e) {
                System.err.println("Ошибка записи данных из файла '" + inputFile + "': " + e.getCause().getMessage() + ". Обработка остановлена.");
                throw e;
            }
        }
    }

    /**
     * Закрывает все процессоры, освобождая ресурсы.
     *
     * @param processors карта процессоров для закрытия
     */
    private void closeProcessors(Map<DataType, DataProcessor> processors) {
        processors.values().forEach(p -> {
            try {
                p.close();
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        });
    }

    /**
     * Выводит статистику по всем типам данных.
     *
     * @param processors карта процессоров, содержащая статистику
     */
    private void printStatistic(Map<DataType, DataProcessor> processors) {
        processors.forEach((key, value) -> value.getStatistics().printStatistic());
    }

}
