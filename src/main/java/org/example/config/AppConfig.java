package org.example.config;

import org.example.enums.StatisticsMode;

import java.nio.file.Path;
import java.util.List;

public record AppConfig(
        List<Path> inputFiles,
        Path outputPath,
        String filePrefix,
        boolean appendMode,
        StatisticsMode statisticsMode
) {
}
