package com.nanda.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nanda.test.constant.PromosiDemosiEnum;
import com.nanda.test.dto.PromosiDemosiDTO;
import com.nanda.test.dto.PromosiDemosiSearchParam;
import com.nanda.test.dto.common.FormDTO;
import com.nanda.test.dto.common.SearchDTO;
import com.nanda.test.entity.Divisi;
import com.nanda.test.entity.Karyawan;
import com.nanda.test.entity.Posisi;
import com.nanda.test.entity.PromosiDemosi;
import com.nanda.test.service.KaryawanService;
import com.nanda.test.service.PromosiDemosiService;

@Controller
public class PromosiDemosiController {

	@Autowired
	private PromosiDemosiService promosiDemosiService;
	@Autowired
	private KaryawanService karyawanService;

	@GetMapping("/promosi-demosi")
	public String promosidemosilist(Model model, PromosiDemosiSearchParam searchparam) {
		SearchDTO<PromosiDemosi> searchdto = promosiDemosiService.searchKaryawan(searchparam.getNik(),
				searchparam.getNama(), searchparam.getTipe(), searchparam.getPage(), searchparam.getSize());
		model.addAttribute("searchdto", searchdto);
		model.addAttribute("searchparam", searchparam);
		return "promosi-demosi-list";
	}

	@GetMapping("/promosi-demosi/add")
	public String add(Model model) {
		SearchDTO<Karyawan> searchdtokaryawan = karyawanService.searchKaryawan(null, null, 0, 1000);
		SearchDTO<Divisi> searchdivisi = karyawanService.searchDivisi();
		SearchDTO<Posisi> searchposisi = karyawanService.searchPosisi();

		model.addAttribute("listpromosidemosi", PromosiDemosiEnum.values());
		model.addAttribute("listdivisi", searchdivisi.getListdata());
		model.addAttribute("listposisi", searchposisi.getListdata());
		model.addAttribute("listkaryawan", searchdtokaryawan.getListdata());
		model.addAttribute("data", new PromosiDemosiDTO());
		return "promosi-demosi-add";
	}

	@PostMapping("/promosi-demosi/add")
	public String save(Model model, RedirectAttributes redirectAttributes, @ModelAttribute PromosiDemosiDTO data) {
		FormDTO<PromosiDemosiDTO> formdto = promosiDemosiService.save(data);
		if (formdto.isSuccess()) {
			redirectAttributes.addFlashAttribute("formdto", formdto);
			return "redirect:/promosi-demosi";
		}

		SearchDTO<Karyawan> searchdtokaryawan = karyawanService.searchKaryawan(null, null, 0, 1000);
		SearchDTO<Divisi> searchdivisi = karyawanService.searchDivisi();
		SearchDTO<Posisi> searchposisi = karyawanService.searchPosisi();

		model.addAttribute("listpromosidemosi", PromosiDemosiEnum.values());
		model.addAttribute("listdivisi", searchdivisi.getListdata());
		model.addAttribute("listposisi", searchposisi.getListdata());
		model.addAttribute("listkaryawan", searchdtokaryawan.getListdata());
		model.addAttribute("data", data);
		model.addAttribute("formdto", formdto);
		return "promosi-demosi-add";
	}

}
