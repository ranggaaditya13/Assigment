package com.perpustakaan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perpustakaan.models.anggotaModel;

@Repository
public interface anggotaRepository extends JpaRepository<anggotaModel, Integer>{

}
