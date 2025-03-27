package com.cobistopaz.file.source.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.function.Supplier;
import java.util.stream.Stream;

@Configuration
public class FileSourceConfig {

    private static final Logger logger = LoggerFactory.getLogger(FileSourceConfig.class);

    @Value("${my.file.dir:/tmp/EDOC_PA/Respuesta}")
    private String fileDir;

    private static final String PATTERN = "^[0-9]{20}_FE_.*\\.(json|txt)$";

    @Bean
    public Supplier<Message<String>> fileSource() {
        return () -> {
            Path directoryPath = Paths.get(fileDir);
            Path processedDir = directoryPath.resolve("Procesados");

            try {
                Files.createDirectories(processedDir);
            } catch (IOException e) {
                logger.error("Error al crear el directorio de procesados: " + processedDir, e);
            }

            try (Stream<Path> paths = Files.list(directoryPath)) {
                File file = paths
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .filter(f -> f.getName().matches(PATTERN))
                        .findFirst()
                        .orElse(null);

                if (file != null) {
                    try {
                        String content = new String(Files.readAllBytes(file.toPath()));
                        logger.info("Archivo encontrado: {}", file.getName());

                        Message<String> message = MessageBuilder
                                .withPayload(content)
                                .setHeader("fileName", file.getName())
                                .build();

                        try {
                            Files.move(file.toPath(), processedDir.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            logger.error("Error al mover el archivo a la carpeta de procesados: " + file.getName(), e);
                        }

                        return message;
                    } catch (IOException e) {
                        logger.error("Error al leer el archivo: " + file.getName(), e);
                    }
                }
            } catch (IOException e) {
                logger.error("Error al listar archivos en el directorio: " + fileDir, e);
            }
            return null;
        };
    }
}