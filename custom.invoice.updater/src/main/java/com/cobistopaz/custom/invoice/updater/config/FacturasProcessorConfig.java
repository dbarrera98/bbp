package com.cobistopaz.custom.invoice.updater.config;

import com.cobistopaz.custom.invoice.updater.dto.FeRespuestaDto;
import com.cobistopaz.custom.invoice.updater.service.FeFacturaService;
import com.cobistopaz.custom.invoice.updater.service.FeRespuestaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class FacturasProcessorConfig {

    private static final Logger logger = LoggerFactory.getLogger(FacturasProcessorConfig.class);

    private final FeFacturaService feFacturaService;
    private final FeRespuestaService feRespuestaService;

    @Value("${facturas.processor.directory:/tmp/EDOC_PA/Procesados/}")
    private String directoryPath;

    @Value("${facturas.processor.retries:20}")
    private int retries;

    @Value("${facturas.processor.delay:3}")
    private int delay;

    public FacturasProcessorConfig(FeFacturaService feFacturaService, FeRespuestaService feRespuestaService) {
        this.feFacturaService = feFacturaService;
        this.feRespuestaService = feRespuestaService;
    }

    @Bean
    public Function<FeRespuestaDto, FeRespuestaDto> validarFactura() {
        return feRespuestaDto -> {
            logger.info("Recibido -> ptoFac={}, seqNos={}, tipo={}, cufe={}, modo={}, fecha={}",
                    feRespuestaDto.getPtoFac(), feRespuestaDto.getSeqNos(), feRespuestaDto.getTipo(),
                    feRespuestaDto.getCufe(), feRespuestaDto.getModo(), feRespuestaDto.getFecha());

            if (feRespuestaDto.getPtoFac() == null || feRespuestaDto.getSeqNos() == null || feRespuestaDto.getModo() == null) {
                logger.warn("Datos incompletos recibidos, ptoFac={}, seqNos={}, modo={}",
                        feRespuestaDto.getPtoFac(), feRespuestaDto.getSeqNos(), feRespuestaDto.getModo());
                return feRespuestaDto;
            }

            if (feRespuestaService.isFacturaDuplicada(feRespuestaDto.getPtoFac(), feRespuestaDto.getSeqNos(), feRespuestaDto.getModo())) {
                logger.info("Factura duplicada detectada: ptoFac={}, seqNos={}, modo={}. Se descarta el registro.",
                        feRespuestaDto.getPtoFac(), feRespuestaDto.getSeqNos(), feRespuestaDto.getModo());
                return null;
            }

            if (feFacturaService.isArchivoProcesado(feRespuestaDto.getPtoFac(), feRespuestaDto.getSeqNos())) {
                logger.info("El archivo ya ha sido procesado: ptoFac={}, seqNos={}. Se descarta el registro.",
                        feRespuestaDto.getPtoFac(), feRespuestaDto.getSeqNos());
                return null;
            }

            try {
                processFileAndActualizarFactura(feRespuestaDto);
            } catch (Exception e) {
                logger.error("Error al procesar la factura: ", e);
            }

            return feRespuestaDto;
        };
    }

    private void processFileAndActualizarFactura(FeRespuestaDto feRespuestaDto) throws Exception {
        String pattern = ".*_FE_" + feRespuestaDto.getPtoFac() + "_" + feRespuestaDto.getSeqNos() + "\\.json";
        boolean fileExists = buscarArchivoConPatron(directoryPath, pattern, retries, delay);

        if (fileExists) {
            feFacturaService.actualizarFactura(feRespuestaDto.getPtoFac(), feRespuestaDto.getSeqNos());
        } else {
            logger.warn("No se actualiza porque el archivo no está en el directorio.");
        }
    }

    private boolean buscarArchivoConPatron(String directoryPath, String pattern, int retries, int delay) {
        boolean fileExists = false;
        Path path = Paths.get(directoryPath);

        for (int i = 0; i < retries; i++) {
            try (Stream<Path> paths = Files.list(path)) {
                List<Path> pathList = paths.collect(Collectors.toList());

                pathList.forEach(p -> logger.debug("Archivo encontrado en directorio: {}", p.getFileName().toString()));

                fileExists = pathList.stream()
                        .filter(Files::isRegularFile)
                        .anyMatch(p -> Pattern.compile(pattern).matcher(p.getFileName().toString()).find());

                if (fileExists) {
                    break;
                } else {
                    logger.info("Archivo con patrón {} no encontrado. Reintentando en {} segundos...", pattern, delay);
                    TimeUnit.SECONDS.sleep(delay);
                }
            } catch (Exception e) {
                logger.error("Error al listar archivos en el directorio: " + directoryPath, e);
            }
        }

        return fileExists;
    }
}