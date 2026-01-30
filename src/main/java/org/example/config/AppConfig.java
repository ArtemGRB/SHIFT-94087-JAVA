package org.example.config;

import org.example.enums.StatisticsMode;

import java.nio.file.Path;
import java.util.List;


/**
 * Конфигурация параметров приложения для обработки и сортировки файлов.
 * Класс инкапсулирует настройки, полученные из аргументов командной строки.
 *
 * <p>Поддерживаемые параметры:
 * <ul>
 *   <li><code>-p PREFIX</code> - префикс имен выходных файлов (сохраняется в {@code filePrefix})</li>
 *   <li><code>-o PATH</code> - директория для сохранения результатов (сохраняется в {@code outputPath})</li>
 *   <li><code>-f</code> - режим полной статистики (устанавливает {@code statisticsMode = FULL})</li>
 *   <li><code>-s</code> - режим краткой статистики (устанавливает {@code statisticsMode = SHORT},
 *       если не указан флаг {@code -f})</li>
 *   <li><code>-a</code> - режим добавления к существующим файлам (устанавливает {@code appendMode = true})</li>
 * </ul>
 *
 * <p>Аргументы без флагов интерпретируются как имена входных файлов (сохраняются в {@code inputFiles}).
 */

public record AppConfig(
        List<Path> inputFiles,
        Path outputPath,
        String filePrefix,
        boolean appendMode,
        StatisticsMode statisticsMode
) {
}
