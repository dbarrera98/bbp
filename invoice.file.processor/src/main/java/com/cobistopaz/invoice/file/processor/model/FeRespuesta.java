package com.cobistopaz.invoice.file.processor.model;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "fe_respuesta")
public class FeRespuesta implements Serializable {

    @EmbeddedId
    private FeRespuestaId id;

    @Column(name = "re_cufe", nullable = false)
    private String reCufe;
    

    @Column(name = "re_error")
    private String reError;

    @Column(name = "re_estado")
    private String reEstado;

    @Column(name = "re_fecha_aut")
    private LocalDateTime reFechaAut;

    @Column(name = "re_mensaje_rspta")
    private String reMensajeRspta;

    @Column(name = "re_num_autorizacion")
    private String reNumAutorizacion;

    @Column(name = "re_secuencial_erp")
    private String reSecuencialErp;

    @Column(name = "re_url_code_qr")
    private String reUrlCodeQr;

    public FeRespuestaId getId() {
        return id;
    }

    public void setId(FeRespuestaId id) {
        this.id = id;
    }

    public String getReCufe() {
        return reCufe;
    }

    public void setReCufe(String reCufe) {
        this.reCufe = reCufe;
    }
    
    public String getReError() {
        return reError;
    }

    public void setReError(String reError) {
        this.reError = reError;
    }

    public String getReEstado() {
        return reEstado;
    }

    public void setReEstado(String reEstado) {
        this.reEstado = reEstado;
    }

    public LocalDateTime getReFechaAut() {
        return reFechaAut;
    }

    public void setReFechaAut(LocalDateTime reFechaAut) {
        this.reFechaAut = reFechaAut;
    }

    public String getReMensajeRspta() {
        return reMensajeRspta;
    }

    public void setReMensajeRspta(String reMensajeRspta) {
        this.reMensajeRspta = reMensajeRspta;
    }

    public String getReNumAutorizacion() {
        return reNumAutorizacion;
    }

    public void setReNumAutorizacion(String reNumAutorizacion) {
        this.reNumAutorizacion = reNumAutorizacion;
    }

    public String getReSecuencialErp() {
        return reSecuencialErp;
    }

    public void setReSecuencialErp(String reSecuencialErp) {
        this.reSecuencialErp = reSecuencialErp;
    }

    public String getReUrlCodeQr() {
        return reUrlCodeQr;
    }

    public void setReUrlCodeQr(String reUrlCodeQr) {
        this.reUrlCodeQr = reUrlCodeQr;
    }
 }
