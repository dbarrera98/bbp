package com.cobistopaz.custom.invoice.updater.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeRespuestaDto {

    @JsonProperty("re_pto_fac")
    private String ptoFac;

    @JsonProperty("re_seqnos")
    private Integer seqNos;

    @JsonProperty("re_tipo")
    private String tipo;

    @JsonProperty("re_cufe")
    private String cufe;

    @JsonProperty("re_modo")
    private String modo;

    @JsonProperty("re_fecha")
    private String fecha;

    public String getPtoFac() {
        return ptoFac;
    }

    public void setPtoFac(String ptoFac) {
        this.ptoFac = ptoFac;
    }

    public Integer getSeqNos() {
        return seqNos;
    }

    public void setSeqNos(Integer seqNos) {
        this.seqNos = seqNos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCufe() {
        return cufe;
    }

    public void setCufe(String cufe) {
        this.cufe = cufe;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}