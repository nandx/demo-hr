package com.nanda.test.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nanda.test.common.CommonUtils;
import com.nanda.test.dto.PromosiDemosiDTO;
import com.nanda.test.dto.common.FormDTO;
import com.nanda.test.dto.common.SearchDTO;
import com.nanda.test.dto.common.SearchDTO.Pagedata;
import com.nanda.test.entity.Divisi;
import com.nanda.test.entity.Karyawan;
import com.nanda.test.entity.Posisi;
import com.nanda.test.entity.PromosiDemosi;
import com.nanda.test.repository.DivisiRepository;
import com.nanda.test.repository.KaryawanRepository;
import com.nanda.test.repository.PosisiRepository;
import com.nanda.test.repository.PromosiDemosiRepository;

@Service
public class PromosiDemosiService {

	@Autowired
	private PromosiDemosiRepository promosiDemosiRepository;
	@Autowired
	private KaryawanRepository karyawanRepository;
	@Autowired
	private PosisiRepository posisiRepository;
	@Autowired
	private DivisiRepository divisiRepository;

	public SearchDTO<PromosiDemosi> searchKaryawan(String nik, String namakaryawan, String tipe, int pagenumber,
			int pagesize) {
		Pageable pageable = PageRequest.of(CommonUtils.indexpage(pagenumber), pagesize);
		Page<PromosiDemosi> pagePromosiDemosi = promosiDemosiRepository.getListPromosiDemosi(CommonUtils.likeQuery(nik),
				CommonUtils.likeQuery(namakaryawan), CommonUtils.likeQuery(tipe), pageable);
		SearchDTO<PromosiDemosi> searchdto = new SearchDTO<PromosiDemosi>();
		searchdto.setListdata(pagePromosiDemosi.getContent());
		searchdto.setPagedata(new Pagedata(pagenumber, pagesize, pagePromosiDemosi.getTotalPages(),
				pagePromosiDemosi.getTotalElements()));
		return searchdto;
	}

	public FormDTO<PromosiDemosiDTO> save(PromosiDemosiDTO dto) {

		if (dto.getIdkaryawan() == null)
			return new FormDTO<PromosiDemosiDTO>("Karyawan belum dipilih", "Karyawan belum dipilih.", dto);

		if (!CommonUtils.isNotEmpty(dto.getTipe()))
			return new FormDTO<PromosiDemosiDTO>("Tipe belum dipilih", "Tipe belum dipilih.", dto);

		if (dto.getIddivisito() == null)
			return new FormDTO<PromosiDemosiDTO>("Divisi belum dipilih", "Divisi belum dipilih.", dto);

		if (dto.getIdposisito() == null)
			return new FormDTO<PromosiDemosiDTO>("Posisi belum dipilih", "Posisi belum dipilih.", dto);

		Optional<Karyawan> optional = karyawanRepository.findById(dto.getIdkaryawan());
		if (optional.isEmpty())
			return new FormDTO<PromosiDemosiDTO>("Karyawan tidak ditemukan", "Karyawan tidak ditemukan.", dto);

		Optional<Posisi> optionalposisi = posisiRepository.findById(dto.getIdposisito());
		if (optionalposisi.isEmpty())
			return new FormDTO<PromosiDemosiDTO>("Posisi yang dituju tidak ditemukan",
					"Posisi yang dituju tidak ditemukan.", dto);

		Optional<Divisi> optionaldivisi = divisiRepository.findById(dto.getIddivisito());
		if (optionaldivisi.isEmpty())
			return new FormDTO<PromosiDemosiDTO>("Divisi yang dituju tidak ditemukan",
					"Divisi yang dituju tidak ditemukan.", dto);

		Karyawan karyawan = optional.get();
		PromosiDemosi data = new PromosiDemosi();
		data.setKaryawan(karyawan);
		data.setDivisifrom(karyawan.getDivisi());
		data.setPosisifrom(karyawan.getPosisi());
		data.setTipe(dto.getTipe());
		data.setPosisito(optionalposisi.get());
		data.setDivisito(optionaldivisi.get());
		promosiDemosiRepository.save(data);

		return new FormDTO<PromosiDemosiDTO>("Data berhasil disimpan.");
	}

}
