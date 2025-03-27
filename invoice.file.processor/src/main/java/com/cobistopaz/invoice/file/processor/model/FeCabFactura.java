package com.cobistopaz.invoice.file.processor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "fe_cab_factura", schema = "dbo", catalog = "cob_fact_electronica")
public class FeCabFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cf_seqnos")
    private Integer cfSeqnos;

    @Column(name = "cf_pto_fac")
    private String cfPtoFac;

    @Column(name = "cf_cufe")
    private String cfCufe;

    @Column(name = "cf_estado")
    private String cfEstado;
    
    public Integer getCfSeqnos() {
        return cfSeqnos;
    }

    public void setCfSeqnos(Integer cfSeqnos) {
        this.cfSeqnos = cfSeqnos;
    }

    public String getCfPtoFac() {
        return cfPtoFac;
    }

    public void setCfPtoFac(String cfPtoFac) {
        this.cfPtoFac = cfPtoFac;
    }

    public String getCfCufe() {
        return cfCufe;
    }

    public void setCfCufe(String cfCufe) {
        this.cfCufe = cfCufe;
    }
    
    public String getCfEstado() {
    	return cfEstado;
    	
    }
    
    public void setCfEstado(String cfEstado) {
    	this.cfEstado = cfEstado;
    }
}
