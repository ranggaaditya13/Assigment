package com.perpustakaan.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perpustakaan.models.bukuModel;
import com.perpustakaan.repositories.bukuRepository;

@Service
@Transactional
public class bukuService {
	
	@Autowired
	private bukuRepository br;
	
	public List<bukuModel> dataAll(){
		return br.findAll();
	}

	public Optional<bukuModel> checkbukuById(int id) {
		return br.findById(id);
	}
	
	public bukuModel addBuku(bukuModel buku){
		buku.setJudul(buku.getJudul());
		buku.setPenulis(buku.getPenulis());
		buku.setPenerbit(buku.getPenerbit());
		buku.setTahun_terbit(buku.getTahun_terbit());
		return br.save(buku);
	}
	
	public bukuModel updateBuku(int id, bukuModel buku){
		bukuModel model = br.findById(id).get();
		
		model.setJudul(buku.getJudul());
		model.setPenulis(buku.getPenulis());
		model.setPenerbit(buku.getPenerbit());
		model.setTahun_terbit(buku.getTahun_terbit());
		return br.save(model);
	}
	
	public void deleteBook(int id) {
		br.deleteById(id);
	}
}
