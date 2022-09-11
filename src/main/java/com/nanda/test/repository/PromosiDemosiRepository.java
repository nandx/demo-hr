package com.nanda.test.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nanda.test.entity.PromosiDemosi;

@Repository
public interface PromosiDemosiRepository extends JpaRepository<PromosiDemosi, Long> {

	@Query("SELECT pd FROM PromosiDemosi pd JOIN pd.karyawan emp WHERE emp.nik LIKE :p_nik "
			+ " AND emp.namakaryawan LIKE :p_namakaryawan AND pd.tipe LIKE :p_tipe ")
	public Page<PromosiDemosi> getListPromosiDemosi(@Param("p_nik") String nik,
			@Param("p_namakaryawan") String namakaryawan, @Param("p_tipe") String tipe, Pageable pageable);

}
