package com.nanda.test.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import lombok.Data;

@Data
@Entity
@Table(name = "promosidemosi")
public class PromosiDemosi {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "karyawan_id")
	private Karyawan karyawan;

	@Column(name = "tipe")
	private String tipe;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "divisifrom_id")
	private Divisi divisifrom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "divisito_id")
	private Divisi divisito;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "posisifrom_id")
	private Posisi posisifrom;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "posisito_id")
	private Posisi posisito;

	@CreationTimestamp
	@Column(name = "createddate")
	private Date createddate;

}
