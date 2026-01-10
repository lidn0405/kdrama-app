package com.lidn.kdrama_app.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lidn.kdrama_app.dto.DramaDto;
import com.lidn.kdrama_app.service.dramaServices.DramaService;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/dramas")
public class DramaController {
    private DramaService dramaService;

    public DramaController(DramaService dramaService) {
        this.dramaService = dramaService;
    }

    @GetMapping("/")
    public List<DramaDto> getDramas() {
        return dramaService.getDramas();
    }

    @GetMapping("/{id}")
    public DramaDto getDrama(@PathVariable Long id) {
        return dramaService.getDrama(id);
    }

    @PostMapping("/")
    public DramaDto createDrama(@RequestBody DramaDto dramaDto) {
        DramaDto saved = dramaService.createDrama(dramaDto);
        return saved;
    }

    @PutMapping("/{id}")
    public DramaDto updateDrama(@PathVariable Long id, @RequestBody DramaDto dramaDto) {
        DramaDto saved = dramaService.updateDrama(id, dramaDto);
        return saved;
    }

    @DeleteMapping("/{id}")
    public void deleteDrama(@PathVariable Long id) {
        dramaService.deleteDrama(id);
    }
    
    
    
}
