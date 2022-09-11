package com.nanda.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nanda.test.common.CommonUtils;
import com.nanda.test.dto.common.FormDTO;
import com.nanda.test.dto.common.KaryawanDTO;
import com.nanda.test.dto.common.SearchDTO;
import com.nanda.test.dto.common.SearchDTO.Pagedata;
import com.nanda.test.entity.Divisi;
import com.nanda.test.entity.Karyawan;
import com.nanda.test.entity.Posisi;
import com.nanda.test.repository.DivisiRepository;
import com.nanda.test.repository.KaryawanRepository;
import com.nanda.test.repository.PosisiRepository;

@Service
public class KaryawanService {

	@Autowired
	private DivisiRepository divisiRepository;
	@Autowired
	private PosisiRepository posisiRepository;
	@Autowired
	private KaryawanRepository karyawanRepository;

	public SearchDTO<Karyawan> searchKaryawan(String nik, String namakaryawan, int pagenumber, int pagesize) {
		Pageable pageable = PageRequest.of(CommonUtils.indexpage(pagenumber), pagesize);
		Page<Karyawan> pageKaryawan = karyawanRepository.getListKaryawan(CommonUtils.likeQuery(nik),
				CommonUtils.likeQuery(namakaryawan), pageable);
		SearchDTO<Karyawan> searchdto = new SearchDTO<Karyawan>();
		searchdto.setListdata(pageKaryawan.getContent());
		searchdto.setPagedata(
				new Pagedata(pagenumber, pagesize, pageKaryawan.getTotalPages(), pageKaryawan.getTotalElements()));
		return searchdto;
	}

	public SearchDTO<Divisi> searchDivisi() {
		List<Divisi> listdivisi = divisiRepository.findAll();
		SearchDTO<Divisi> searchdto = new SearchDTO<Divisi>();
		searchdto.setListdata(listdivisi);
		return searchdto;
	}

	public SearchDTO<Posisi> searchPosisi() {
		List<Posisi> listposisi = posisiRepository.findAll();
		SearchDTO<Posisi> searchdto = new SearchDTO<Posisi>();
		searchdto.setListdata(listposisi);
		return searchdto;
	}

	public FormDTO<Karyawan> save(Karyawan data) {

		if (!CommonUtils.isNotEmpty(data.getNik()))
			return new FormDTO<Karyawan>("NIK tidak boleh kosong.", "NIK tidak boleh kosong.", data);

		if (!CommonUtils.isNotEmpty(data.getNamakaryawan()))
			return new FormDTO<Karyawan>("Nama Karyawan tidak boleh kosong.", "Nama Karyawan tidak boleh kosong.",
					data);

		Karyawan karyawan = karyawanRepository.findByNik(data.getNik());
		if (data.getId() == null && karyawan != null) {
			return new FormDTO<Karyawan>("NIK sudah dipakai.", "NIK sudah dipakai.", data);
		}

		karyawanRepository.save(data);
		return new FormDTO<Karyawan>("Data Karyawan berhasil disimpan.", data);
	}

	public SearchDTO<KaryawanDTO> getKaryawanById(Long id) {
		SearchDTO<KaryawanDTO> searchdto = new SearchDTO<KaryawanDTO>();
		searchdto.setListdata(new ArrayList<>());

		Optional<Karyawan> optional = karyawanRepository.findById(id);
		if (optional.isEmpty())
			return searchdto;

		Karyawan data = optional.get();

		Pagedata pagedata = new Pagedata(1, 1, 1, 1);
		KaryawanDTO dto = new KaryawanDTO();
		dto.setId(data.getId());
		dto.setNik(data.getNik());
		dto.setNamakaryawan(data.getNamakaryawan());
		dto.setNamadivisi(data.getDivisi().getNamadivisi());
		dto.setNamaposisi(data.getPosisi().getNamaposisi());

		searchdto.getListdata().add(dto);
		searchdto.setSuccess(true);
		searchdto.setPagedata(pagedata);
		return searchdto;
	}

}
