package com.perpustakaan.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perpustakaan.models.peminjamanModel;
import com.perpustakaan.services.peminjamanService;

@RestController
public class peminjamanController {
	
	@Autowired
	private peminjamanService ps;
	
	@GetMapping("/peminjaman")
	public List<peminjamanModel> getAllData(){
		return ps.SemuaDataPeminjaman();
	}
	
	@PostMapping("/peminjaman")
	public peminjamanModel addPeminjaman(@RequestBody peminjamanModel model) {
		return ps.addPeminjaman(model);
	}
	
	@PutMapping("/peminjaman/{id}")
	public peminjamanModel editPeminjaman(@RequestBody peminjamanModel model , @PathVariable long id) {
		return ps.editPeminjaman(model, id);
	}
	
	@GetMapping("/peminjaman/test")
	public List<Map<String, Object>> listPeminjaman(){
		return ps.listPeminjaman();
	}
	
	@PostMapping("/peminjaman/test")
	public void savePeminjaman(@RequestBody peminjamanModel model) throws Exception {
		ps.savePeminjamanInput(model);
	}
	
	@DeleteMapping("/peminjaman/test/{id}")
	public void deletePeminjaman(@PathVariable long id) {
		ps.deletePeminjaman(id);
	}

}
