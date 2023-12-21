package com.perpustakaan.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perpustakaan.models.anggotaModel;
import com.perpustakaan.models.bukuModel;
import com.perpustakaan.models.peminjamanModel;
import com.perpustakaan.repositories.anggotaRepository;
import com.perpustakaan.repositories.bukuRepository;
import com.perpustakaan.repositories.peminjamanRepository;

@Service
@Transactional
public class peminjamanService {
	
	@Autowired
	private peminjamanRepository pr;
	
	@Autowired
	private anggotaRepository ar;
	
	@Autowired
	private bukuRepository br;
	
	public List<peminjamanModel> SemuaDataPeminjaman(){
		return pr.findAll();
	}
	
	public peminjamanModel addPeminjaman(peminjamanModel model) {
		peminjamanModel temp = new peminjamanModel();
		temp.setId_anggota(model.getId_anggota());
		temp.setId_buku(model.getId_buku());
		temp.setTanggal_peminjaman(model.getTanggal_peminjaman());
		temp.setTanggal_pengembalian(model.getTanggal_pengembalian());
		
		Date date1 = temp.getTanggal_peminjaman();
		Date date2 = temp.getTanggal_pengembalian();
		int totalHariPeminjaman = menghitungLamaPeminjaman(date1, date2);
		int dendaPerHari = 1000;
		int hariPalingLambat = 7;
		if(totalHariPeminjaman > hariPalingLambat) {
			temp.setDenda_keterlambatan((totalHariPeminjaman - hariPalingLambat) * dendaPerHari);
	
		}else {
			temp.setDenda_keterlambatan(0);
		}
		return pr.save(temp);	
		}
	
	public peminjamanModel editPeminjaman(peminjamanModel model, long id) {
		peminjamanModel temp = new peminjamanModel();
		temp = pr.findById(id).get();
		temp.setId_anggota(model.getId_anggota());
		temp.setId_buku(model.getId_buku());
		temp.setTanggal_peminjaman(model.getTanggal_peminjaman());
		temp.setTanggal_pengembalian(model.getTanggal_pengembalian());
		
		Date date1 = temp.getTanggal_peminjaman();
		Date date2 = temp.getTanggal_pengembalian();
		int dendaPerHari = 1000;
		int hariPalingLambat = 7;
		int totalHariPeminjaman = menghitungLamaPeminjaman(date1, date2);
		if(totalHariPeminjaman > hariPalingLambat) {
			temp.setDenda_keterlambatan((totalHariPeminjaman - hariPalingLambat) * dendaPerHari);
	
		}else {
			temp.setDenda_keterlambatan(0);
		}
		return pr.save(temp);
	}
	
	public List<Map<String, Object>> listPeminjaman(){
		return pr.listPeminjaman();
	}
	 
	
	public void savePeminjamanInput(peminjamanModel model) throws Exception {
		peminjamanModel temp = new peminjamanModel();
		temp.setId_anggota(model.getId_anggota());
		temp.setId_buku(model.getId_buku());
		temp.setTanggal_peminjaman(model.getTanggal_peminjaman());
		temp.setTanggal_pengembalian(model.getTanggal_pengembalian());
		
		System.out.println("ID Anggota: " + temp.getId_anggota());
		System.out.println("ID Buku: " + temp.getId_buku());
		System.out.println("Tanggal Peminjaman: " + temp.getTanggal_peminjaman());
		System.out.println("Tanggal Pengembalian: " + temp.getTanggal_pengembalian());
		
		Date date1 = temp.getTanggal_peminjaman();
		Date date2 = temp.getTanggal_pengembalian();
		int totalHariPeminjaman = menghitungLamaPeminjaman(date1, date2);
		int dendaPerHari = 1000;
		int hariPalingLambat = 7;
		if(totalHariPeminjaman > hariPalingLambat) {
			temp.setDenda_keterlambatan((totalHariPeminjaman - hariPalingLambat) * dendaPerHari);
	
		}else {
			temp.setDenda_keterlambatan(0);
		}
		System.out.println(temp.getDenda_keterlambatan());
		
		try {
			Optional<anggotaModel> amodel = ar.findById(temp.getId_anggota());
			Optional<bukuModel> bmodel = br.findById(temp.getId_buku());
			if(amodel.isEmpty() && bmodel.isEmpty()) {
				System.out.println("Buku/Nama Anggota Tidak Ada");
			}else {
				System.out.println("Masuk SIni");
				pr.save(temp);
			}
		} catch (RuntimeException e) {
		    System.out.println("Exception: " + e.getMessage());
		}

	}
	
	public void deletePeminjaman(long id) {
		pr.deleteById(id);
	}
	
	
	
	
	public static int menghitungLamaPeminjaman(Date date1, Date date2) {
		if(date1 == null || date2 == null) {
			return 0;
		}else {
			long diff = date2.getTime() - date1.getTime();
			long diffDay = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	        return Math.abs((int)diffDay);	
		}
    }

}
