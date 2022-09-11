package com.nanda.test.initial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nanda.test.entity.Posisi;
import com.nanda.test.repository.PosisiRepository;

@Component
public class PosisiIntial {

	@Autowired
	private PosisiRepository posisiRepository;

	@PostConstruct
	public void initialData() {
		initialData("Staff");
		initialData("Supervisor");
		initialData("Asisten Manager");
		initialData("Manager");
	}

	public void initialData(String namaposisi) {
		Posisi data = posisiRepository.findByNamaposisi(namaposisi);
		if (data != null)
			return;

		data = new Posisi();
		data.setNamaposisi(namaposisi);
		posisiRepository.save(data);
	}

}
