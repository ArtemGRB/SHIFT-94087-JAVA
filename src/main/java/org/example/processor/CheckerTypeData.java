package org.example.processor;

import org.example.enums.DataType;

/**
 * Класс для определения типа данных в строке.
 */
public class CheckerTypeData {

    /**
     * Определяет тип данных, содержащихся в строке.
     * <p>
     * Проверяет строку в следующем порядке:
     * <ol>
     *   <li>Если строка null или пустая - возвращает STRING</li>
     *   <li>Пытается преобразовать в Long (целое число)</li>
     *   <li>Пытается преобразовать в Double (вещественное число)</li>
     *   <li>Если оба преобразования не удались - возвращает STRING</li>
     * </ol>
     *
     * @param line строка для анализа
     * @return тип данных, определенный для строки
     */
    static public DataType check(String line) {
        if (line == null || line.isBlank()) {
            return DataType.STRING;
        }
        try {
            Long.parseLong(line);
            return DataType.INTEGER;
        } catch (NumberFormatException ignored) {
        }

        try {
            Double.parseDouble(line);
            // Этот метод корректно обработает форматы "1.23", "1.5e-10", а также "Infinity".
            return DataType.FLOAT;
        } catch (NumberFormatException e2) {
            // Если не удалось преобразовать ни в Long, ни в Double, то это точно строка.
            return DataType.STRING;
        }
    }
}
