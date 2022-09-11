package com.nanda.test.initial;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nanda.test.entity.Divisi;
import com.nanda.test.repository.DivisiRepository;

@Component
public class DivisiIntial {

	@Autowired
	private DivisiRepository divisiRepository;

	@PostConstruct
	public void initialData() {
		initialData("IT");
		initialData("HRD");
		initialData("Loading");
		initialData("Ticketing");
	}

	public void initialData(String namadivisi) {
		Divisi data = divisiRepository.findByNamadivisi(namadivisi);
		if (data != null)
			return;

		data = new Divisi();
		data.setNamadivisi(namadivisi);
		divisiRepository.save(data);
	}

}
