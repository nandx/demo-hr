package com.nanda.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromosiDemosiDTO {

	private Long idkaryawan;
	private String nik;
	private String namakaryawan;
	private String namaposisifrom;
	private String namadivisifrom;
	private String tipe;
	private Long idposisito;
	private String namaposisito;
	private Long iddivisito;
	private String namadivisito;

}
