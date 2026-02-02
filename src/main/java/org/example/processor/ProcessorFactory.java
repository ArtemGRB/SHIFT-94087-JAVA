package org.example.processor;

import org.example.config.AppConfig;
import org.example.enums.DataType;

import java.util.HashMap;
import java.util.Map;


/**
 * Фабрика для создания процессоров данных.
 * Создает и инициализирует процессоры для всех типов данных.
 */
public class ProcessorFactory {

    /**
     * Создает карту процессоров для всех типов данных на основе конфигурации приложения.
     *
     * @param appConfig конфигурация приложения
     * @return карта, где ключ - тип данных {@link DataType}, значение - соответствующий {@link DataProcessor}
     */
    static public Map<DataType, DataProcessor> createProcessors(AppConfig appConfig) {

        Map<DataType, DataProcessor> processorMap = new HashMap<>();

        for (DataType dataType : DataType.values()) {
            processorMap.put(dataType, new DataProcessor(appConfig, dataType));
        }

        return processorMap;
    }

}
