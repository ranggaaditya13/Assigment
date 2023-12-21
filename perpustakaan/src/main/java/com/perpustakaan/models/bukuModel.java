package com.perpustakaan.models;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "buku")
public class bukuModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name= "judul")
	private String judul;
	
	@Column(name = "penulis")
	private String penulis;
	
	@Column(name = "penerbit")
	private String penerbit;
	
	@Column(name = "tahun_terbit")
	private int tahun_terbit;

}
