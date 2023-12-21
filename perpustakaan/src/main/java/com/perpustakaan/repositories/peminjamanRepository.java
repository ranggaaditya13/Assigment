package com.perpustakaan.repositories;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.perpustakaan.models.peminjamanModel;

@Repository
public interface peminjamanRepository extends JpaRepository<peminjamanModel, Long>{
	
	@Query(value = "select peminjaman.id,anggota.nama, buku.judul,tanggal_peminjaman,tanggal_pengembalian,denda_keterlambatan from peminjaman join buku on peminjaman.id_buku = buku.id join anggota on peminjaman.id_anggota = anggota.id",nativeQuery = true)
	List<Map<String, Object>> listPeminjaman();
}
