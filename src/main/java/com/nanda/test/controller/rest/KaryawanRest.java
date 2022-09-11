package com.nanda.test.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nanda.test.dto.common.KaryawanDTO;
import com.nanda.test.dto.common.SearchDTO;
import com.nanda.test.service.KaryawanService;

@RestController
public class KaryawanRest {

	@Autowired
	private KaryawanService karyawanService;

	@RequestMapping(method = RequestMethod.GET, path = "/rest/karyawan", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SearchDTO<KaryawanDTO>> getKaryawanById(
			@RequestParam(name = "idkaryawan", defaultValue = "0") Long id) {
		System.out.println("idkaryawan : " + id);
		SearchDTO<KaryawanDTO> searchdto = karyawanService.getKaryawanById(id);
		return new ResponseEntity<SearchDTO<KaryawanDTO>>(searchdto, HttpStatus.OK);
	}

}
