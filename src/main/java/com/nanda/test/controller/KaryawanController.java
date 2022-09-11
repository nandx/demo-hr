package com.nanda.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.nanda.test.dto.KaryawanSearchParam;
import com.nanda.test.dto.common.FormDTO;
import com.nanda.test.dto.common.SearchDTO;
import com.nanda.test.entity.Divisi;
import com.nanda.test.entity.Karyawan;
import com.nanda.test.entity.Posisi;
import com.nanda.test.service.KaryawanService;

@Controller
public class KaryawanController {

	@Autowired
	private KaryawanService karyawanService;

	@GetMapping("/karyawan")
	public String karyawanlist(Model model, KaryawanSearchParam searchparam) {
		SearchDTO<Karyawan> searchdto = karyawanService.searchKaryawan(searchparam.getNik(), searchparam.getNama(),
				searchparam.getPage(), searchparam.getSize());
		model.addAttribute("searchdto", searchdto);
		model.addAttribute("searchparam", searchparam);
		return "karyawan-list";
	}

	@GetMapping("/karyawan/add")
	public String karyawanadd(Model model) {
		SearchDTO<Divisi> searchdivisi = karyawanService.searchDivisi();
		SearchDTO<Posisi> searchposisi = karyawanService.searchPosisi();

		model.addAttribute("listdivisi", searchdivisi.getListdata());
		model.addAttribute("listposisi", searchposisi.getListdata());
		model.addAttribute("karyawan", new Karyawan());
		return "karyawan-add";
	}

	@PostMapping("/karyawan/add")
	public String karyawanaddsave(Model model, @ModelAttribute Karyawan karyawan) {
		FormDTO<Karyawan> formdto = karyawanService.save(karyawan);
		SearchDTO<Divisi> searchdivisi = karyawanService.searchDivisi();
		SearchDTO<Posisi> searchposisi = karyawanService.searchPosisi();

		model.addAttribute("listdivisi", searchdivisi.getListdata());
		model.addAttribute("listposisi", searchposisi.getListdata());
		model.addAttribute("karyawan", formdto.getData());
		model.addAttribute("formdto", formdto);
		return "karyawan-add";
	}

}
