package com.nanda.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nanda.test.entity.Posisi;

@Repository
public interface PosisiRepository extends JpaRepository<Posisi, Long> {

	public Posisi findByNamaposisi(String namaposisi);

}
