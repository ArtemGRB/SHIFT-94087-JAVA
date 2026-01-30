package org.example.processor;

import org.example.config.AppConfig;
import org.example.enums.DataType;

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

    public DataProcessor(AppConfig appConfig, DataType dataType) {
        this.appConfig = appConfig;
        this.outputFileName = appConfig.filePrefix() + dataType.getFileName();
    }

    public void process(String line) {
        try {
            // Lazy инициализация writer
            initializationBufferedWriter();
            bufferedWriter.write(line);
            bufferedWriter.newLine();
            bufferedWriter.flush();
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
}

