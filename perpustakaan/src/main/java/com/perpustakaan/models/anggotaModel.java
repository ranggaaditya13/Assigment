package com.perpustakaan.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="anggota")
public class anggotaModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="nama")
	private String nama;
	
	@Column(name="alamat")
	private String alamat;
	
	@Column(name="nomor_telepon")
	private String nomor_telepon;

}
