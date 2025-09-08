package com.lidn.kdrama_app.service.dramaServices;

import java.util.List;

import com.lidn.kdrama_app.dto.DramaDto;

public interface DramaService {
    List<DramaDto> getDramas();
    DramaDto getDrama(Long id);
    DramaDto updateDrama(Long id, DramaDto dramaDto);
    DramaDto createDrama(DramaDto dramaDto);
    void deleteDrama(Long id);
}
