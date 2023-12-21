package com.perpustakaan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perpustakaan.models.bukuModel;

@Repository
public interface bukuRepository extends JpaRepository<bukuModel, Integer>{

}
