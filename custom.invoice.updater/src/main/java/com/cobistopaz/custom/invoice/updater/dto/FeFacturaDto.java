package com.cobistopaz.custom.invoice.updater.dto;

public class FeFacturaDto{

    private Integer seqNos;
    private String ptoFac;
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
}
