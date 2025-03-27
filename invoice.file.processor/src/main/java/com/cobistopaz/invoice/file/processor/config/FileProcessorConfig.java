package com.cobistopaz.invoice.file.processor.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.cobistopaz.invoice.file.processor.service.FileProcessorService;

import java.util.function.Consumer;

@Component
public class FileProcessorConfig {
    
    private static final Logger logger = LoggerFactory.getLogger(FileProcessorConfig.class);

    private final FileProcessorService fileProcessor;

    public FileProcessorConfig(FileProcessorService fileProcessor) {
        this.fileProcessor = fileProcessor;
    }

    @Bean
    public Consumer<Message<String>> processFile() {
        return message -> {
            String fileContent = message.getPayload();
            String fileName = (String) message.getHeaders().get("fileName");
            
            if (fileContent == null || fileContent.trim().isEmpty()) {
                logger.info("El contenido del archivo está vacío. Se omite parseo.");
                return;
            }

            try {
                fileProcessor.processContent(fileContent, fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}