package com.lidn.kdrama_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lidn.kdrama_app.entity.Drama;

public interface DramaRepository extends JpaRepository<Drama, Long>{

}