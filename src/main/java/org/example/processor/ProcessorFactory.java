package org.example.processor;

import org.example.config.AppConfig;
import org.example.enums.DataType;

import java.util.HashMap;
import java.util.Map;

public class ProcessorFactory {

        public Map<DataType,DataProcessor> createProcessors(AppConfig appConfig){

            Map<DataType,DataProcessor> processorMap = new HashMap<>();

            for (DataType dataType: DataType.values()){
                    processorMap.put(dataType, new DataProcessor(appConfig,dataType));
            }

            return processorMap;
        }

}
