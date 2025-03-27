package com.cobistopaz.custom.invoice.updater.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "fe_respuesta")
public class FeRespuestaEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "re_seqnos")
	private Integer seqNos;
	
	@Column (name = "re_modo")
	private String modo;
	
	@Column (name = "re_fecha")
	private Timestamp fecha;
	
	@Column (name = "re_pto_fac")
	private String ptoFac;
	
	@Column (name = "re_cufe")
	private String cufe;
	
	@Column (name = "re_tipo")
	private String tipo;
	
	public Integer getSeqNos() {
		return seqNos;
	}

	public void setSeqNos(Integer seqNos) {
		this.seqNos = seqNos;
	}

	public String getModo() {
		return modo;
	}

	public void setModo(String modo) {
		this.modo = modo;
	}

	public Timestamp getFecha() {
		return fecha;
	}

	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	public String getPtoFac() {
		return ptoFac;
	}

	public void setPtoFac(String ptoFac) {
		this.ptoFac = ptoFac;
	}

	public String getCufe() {
		return cufe;
	}

	public void setCufe(String cufe) {
		this.cufe = cufe;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
