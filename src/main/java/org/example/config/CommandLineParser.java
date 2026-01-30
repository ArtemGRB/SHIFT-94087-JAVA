package org.example.config;

import org.example.enums.StatisticsMode;
import org.example.exeption.ConfigException;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CommandLineParser {

    public AppConfig parse(String[] args) {
        List<Path> inputFiles = new ArrayList<>();
        Path outputPath = Paths.get("");
        String filePrefix = "";
        boolean appendMode = false;
        StatisticsMode statisticsMode = StatisticsMode.NONE;

        if (args.length == 0) {
            throw new ConfigException(
                    "Не указаны аргументы командной строки.\n" +
                            "Формат использования: java -jar util.jar [опции] входные_файлы...\n" +
                            "Пример: java -jar util.jar -o ./output -p result_ file1.txt file2.txt"
            );
        }

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {

                case "-o":
                    if (i + 1 < args.length) {
                        outputPath = Paths.get(args[++i]);
                    } else {
                        throw new ConfigException(
                                "Опция -o требует указания пути к директории вывода.\n" +
                                        "Формат: -o <путь_к_директории>"
                        );
                    }
                    break;


                case "-p":
                    if (i + 1 < args.length) {
                        filePrefix = args[++i];
                    } else {
                        throw new ConfigException(
                                "Опция -p требует указания префикса.\n" +
                                        "Формат: -p <префикс>");
                    }
                    break;


                case "-a":
                    appendMode = true;
                    break;


                case "-s": {
                    if (statisticsMode == StatisticsMode.NONE) {
                        statisticsMode = StatisticsMode.SHORT;
                    }
                    break;
                }


                case "-f":
                    statisticsMode = StatisticsMode.FULL;
                    break;


                default:
                    if (args[i].startsWith("-")) {
                        System.err.println("Предупреждение: неизвестная опция '" + args[i] + "'. Она будет проигнорирована.");
                    } else {
                        inputFiles.add(Paths.get(args[i]));
                    }
                    break;
            }
        }

        if (inputFiles.isEmpty()) {
            throw new ConfigException(
                    "Не указаны входные файлы для обработки.\n" +
                            "Укажите хотя бы один файл после опций (например: file1.txt file2.txt).\n" +
                            "Проверьте, что имена файлов указаны корректно.");
        }

        return new AppConfig(inputFiles, outputPath, filePrefix, appendMode, statisticsMode);
    }
}
