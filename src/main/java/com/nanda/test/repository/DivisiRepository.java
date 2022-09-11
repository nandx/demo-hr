package com.nanda.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nanda.test.entity.Divisi;

@Repository
public interface DivisiRepository extends JpaRepository<Divisi, Long> {

	public Divisi findByNamadivisi(String namadivisi);

}
