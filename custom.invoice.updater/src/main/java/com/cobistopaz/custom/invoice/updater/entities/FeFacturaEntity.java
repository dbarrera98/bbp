package com.cobistopaz.custom.invoice.updater.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "fe_cab_factura")
public class FeFacturaEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cf_seqnos")
    private Integer seqNos;
	
	@Column(name = "cf_pto_fac")
    private String ptoFac;
	
	@Column(name = "cf_estado")
    private String estado;

	public Integer getSeqNos() {
		return seqNos;
	}

	public void setSeqNos(Integer seqNos) {
		this.seqNos = seqNos;
	}

	public String getPtoFac() {
		return ptoFac;
	}

	public void setPtoFac(String ptoFac) {
		this.ptoFac = ptoFac;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
