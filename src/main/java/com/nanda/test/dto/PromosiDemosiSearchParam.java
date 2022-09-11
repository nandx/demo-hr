package com.nanda.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PromosiDemosiSearchParam {

	private String nik = "";
	private String nama = "";
	private String tipe = "";
	private Integer page = 1;
	private Integer size = 5;

}
