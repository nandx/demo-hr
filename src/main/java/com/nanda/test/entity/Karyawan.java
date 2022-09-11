package com.nanda.test.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "karyawan")
public class Karyawan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nik", unique = true)
	private String nik;

	@Column(name = "namakaryawan")
	private String namakaryawan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "posisi_id")
	private Posisi posisi;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "divisi_id")
	private Divisi divisi;

}
