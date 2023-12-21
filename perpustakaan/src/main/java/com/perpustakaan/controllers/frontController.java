package com.perpustakaan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/front")
public class frontController {
	
	@GetMapping("/dashboard")
	public String halaman() {
		return "halaman";
	}
	
	@GetMapping("/buku")
	public String buku() {
		return "buku";
	}
	
	@GetMapping("/anggota")
	public String anggota() {
		return "anggota";
	}
	
	@GetMapping("/peminjaman")
	public String peminjaman() {
		return "peminjaman";
	}

}
