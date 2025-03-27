package com.cobistopaz.invoice.file.processor.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FeRespuestaId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "re_pto_fac")
    private String rePtoFac;

    @Column(name = "re_seqnos")
    private Integer reSeqnos;
    
    @Column(name = "re_modo")
    private String reModo;

    public FeRespuestaId() {
    }

    public FeRespuestaId(String rePtoFac, Integer reSeqnos, String reModo) {
        this.rePtoFac = rePtoFac;
        this.reSeqnos = reSeqnos;
        this.reModo = reModo;
    }

    public String getRePtoFac() {
        return rePtoFac;
    }

    public void setRePtoFac(String rePtoFac) {
        this.rePtoFac = rePtoFac;
    }

    public Integer getReSeqnos() {
        return reSeqnos;
    }

    public void setReSeqnos(Integer reSeqnos) {
        this.reSeqnos = reSeqnos;
    }
    
    public String getReModo() {
        return reModo;
    }

    public void setReModo(String reModo) {
        this.reModo = reModo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeRespuestaId that = (FeRespuestaId) o;
        return Objects.equals(rePtoFac, that.rePtoFac) && Objects.equals(reSeqnos, that.reSeqnos)&&
                Objects.equals(reModo, that.reModo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rePtoFac, reSeqnos, reModo);
    }

    @Override
    public String toString() {
        return "FeRespuestaId{" +
                "rePtoFac='" + rePtoFac + '\'' +
                ", reSeqnos=" + reSeqnos +
                ", reModo='" + reModo + '\'' +
                '}';
    }
}
