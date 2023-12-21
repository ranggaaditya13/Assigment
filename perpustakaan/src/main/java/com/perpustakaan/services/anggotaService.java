package com.perpustakaan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perpustakaan.models.anggotaModel;
import com.perpustakaan.repositories.anggotaRepository;

@Service
@Transactional
public class anggotaService {
	
	@Autowired
	private anggotaRepository ar;
	
	public List<anggotaModel> dataAll(){
		return ar.findAll();
	}
	
	public anggotaModel tambahAnggota(anggotaModel model) {
		anggotaModel temp = new anggotaModel();
		temp.setNama(model.getNama());
		temp.setAlamat(model.getAlamat());
		temp.setNomor_telepon(model.getNomor_telepon());
		return ar.save(temp);
	}
	
	public anggotaModel editAnggota(int id,anggotaModel model) {
		anggotaModel temp = new anggotaModel();
		temp = ar.findById(id).get();
		temp.setNama(model.getNama());
		temp.setAlamat(model.getAlamat());
		temp.setNomor_telepon(model.getNomor_telepon());
		return ar.save(temp);
	}
	
	public void deleteAnggota(int id) {
		ar.deleteById(id);
	}

}
