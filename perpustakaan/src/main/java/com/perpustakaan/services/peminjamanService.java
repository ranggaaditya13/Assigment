package com.perpustakaan.services;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perpustakaan.models.peminjamanModel;
import com.perpustakaan.repositories.peminjamanRepository;

@Service
@Transactional
public class peminjamanService {
	
	@Autowired
	private peminjamanRepository pr;
	
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
		int dendaPerHari = 1000;
		temp.setDenda_keterlambatan(1000);
		return pr.save(temp)
;	}
	
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
