package com.nanda.test.dto.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class KaryawanDTO {

	private Long id;
	private String nik;
	private String namakaryawan;
	private String namaposisi;
	private String namadivisi;

}
