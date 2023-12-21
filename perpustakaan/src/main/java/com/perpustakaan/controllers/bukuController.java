package com.perpustakaan.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.perpustakaan.models.bukuModel;
import com.perpustakaan.services.bukuService;

@RestController
public class bukuController {
	
	@Autowired
	private bukuService bs;
	
	
	@GetMapping("/buku")
	@CrossOrigin(origins = "http://localhost:3000")
	public List<bukuModel> dataAll(){
		return bs.dataAll();
	}
	
	@GetMapping("/buku/{id}")
	public Optional<bukuModel> findBukuById(@PathVariable int id){
		return bs.checkbukuById(id);
	}
	
	@PostMapping("/buku")
	public bukuModel addBuku(@RequestBody bukuModel model) {
		return bs.addBuku(model);
	}
	
	@PutMapping("/buku/{id}")
	public bukuModel updateBuku(@RequestBody bukuModel buku, @PathVariable int id) {
		return bs.updateBuku(id, buku);
	}

	@DeleteMapping("/buku/{id}")
	public void deleteBook(@PathVariable int id) {
	 bs.deleteBook(id);
	}
}
