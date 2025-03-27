package com.cobistopaz.invoice.file.processor.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String date = p.getText();
        if ("0001-01-01T00:00:00".equals(date)) {
            return null;
        }
        try {
            return LocalDateTime.parse(date, formatter1);
        } catch (DateTimeParseException e) {
            try {
                return LocalDateTime.parse(date, formatter2);
            } catch (DateTimeParseException ex) {
                throw new JsonProcessingException("No se pudo deserializar LocalDateTime", ex) {};
            }
        }
    }
}