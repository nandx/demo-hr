package com.nanda.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nanda.test.entity.Karyawan;

@Repository
public interface KaryawanRepository extends JpaRepository<Karyawan, Long> {

	public Karyawan findByNik(String nik);

	@Query("SELECT karyawan FROM Karyawan karyawan WHERE karyawan.nik LIKE :p_nik AND karyawan.namakaryawan LIKE :p_namakaryawan ")
	public Page<Karyawan> getListKaryawan(@Param("p_nik") String nik, @Param("p_namakaryawan") String namakaryawan,
			Pageable pageable);

}
