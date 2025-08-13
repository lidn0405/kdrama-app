package com.lidn.kdrama_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lidn.kdrama_app.models.Drama;

public interface DramaRepository extends JpaRepository<Long, Drama>{

}