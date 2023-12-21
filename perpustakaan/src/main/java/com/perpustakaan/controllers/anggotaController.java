package com.perpustakaan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perpustakaan.models.anggotaModel;
import com.perpustakaan.services.anggotaService;

@RestController
public class anggotaController {
	
	@Autowired
	private anggotaService as;
	
	@GetMapping("/anggota")
	public List<anggotaModel> dataAll(){
		return as.dataAll();
	}
	
	@PostMapping("/anggota")
	public anggotaModel tambahAnggota(@RequestBody anggotaModel model) {
		return as.tambahAnggota(model);
	}
	
	@PutMapping("/anggota/{id}")
	public anggotaModel editAnggota(@PathVariable int id, @RequestBody anggotaModel model) {
		return as.editAnggota(id, model);
	}
	
	@DeleteMapping("/anggota/{id}")
	public void deleteAnggota(@PathVariable int id) {
	 as.deleteAnggota(id);;
	}

}
