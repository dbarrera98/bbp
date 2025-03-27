package com.cobistopaz.invoice.file.processor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonResponse {

    @JsonProperty("CodigoError")
    private String codigoError;

    @JsonProperty("FechaAutorizacion")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaAutorizacion;

    @JsonProperty("UrlCodeQR")
    private String urlCodeQR;

    @JsonProperty("Cufe")
    private String cufe;

    @JsonProperty("Estado")
    private String estado;

    @JsonProperty("MensajeRespuesta")
    private String mensajeRespuesta;

    @JsonProperty("NumAutorizacion")
    private String numAutorizacion;

    @JsonProperty("SecuencialERP")
    private String secuencialERP;


    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public LocalDateTime getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    public void setFechaAutorizacion(LocalDateTime fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    public String getUrlCodeQR() {
        return urlCodeQR;
    }

    public void setUrlCodeQR(String urlCodeQR) {
        this.urlCodeQR = urlCodeQR;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMensajeRespuesta() {
        return mensajeRespuesta;
    }

    public void setMensajeRespuesta(String mensajeRespuesta) {
        this.mensajeRespuesta = mensajeRespuesta;
    }

    public String getNumAutorizacion() {
        return numAutorizacion;
    }

    public void setNumAutorizacion(String numAutorizacion) {
        this.numAutorizacion = numAutorizacion;
    }

    public String getSecuencialERP() {
        return secuencialERP;
    }

    public void setSecuencialERP(String secuencialERP) {
        this.secuencialERP = secuencialERP;
    }
}