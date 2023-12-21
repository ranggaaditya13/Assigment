package com.perpustakaan.models;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "peminjaman")
public class peminjamanModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name ="id_anggota")
	private int id_anggota;
	
	@Column(name="id_buku")
	private int id_buku;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	@Column(name = "tanggal_peminjaman")
	private Date tanggal_peminjaman;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd")
	@Column(name = "tanggal_pengembalian")
	private Date tanggal_pengembalian;
	
	
	@Column(name = "denda_keterlambatan")
	private int denda_keterlambatan;

}
