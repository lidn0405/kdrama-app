package com.lidn.kdrama_app.services.dramaServices;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lidn.kdrama_app.dto.DramaDto;
import com.lidn.kdrama_app.models.Drama;
import com.lidn.kdrama_app.models.reviews.Review;
import com.lidn.kdrama_app.models.reviews.ReviewKey;
import com.lidn.kdrama_app.repositories.DramaRepository;
import com.lidn.kdrama_app.repositories.ReviewRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DramaServiceImplementation implements DramaService {
    DramaRepository dramaRepository;
    ReviewRepository reviewRepository;

    public DramaServiceImplementation(DramaRepository dramaRepository, ReviewRepository reviewRepository) {
        this.dramaRepository = dramaRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<DramaDto> getDramas() {
        List<Drama> dramas = dramaRepository.findAll();
        return dramas.stream()
                .map((drama) -> new DramaDto(drama))
                .collect(Collectors.toList());
    }

    @Override
    public DramaDto getDrama(Long id) {
        Drama drama = dramaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Drama not found with id: " + id));
        
        return new DramaDto(drama);
    }

    @Override
    public DramaDto updateDrama(Long id, DramaDto dramaDto) {
        Drama drama = dramaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Drama not found with id: " + id));

        drama.setName(dramaDto.getName());
        drama.setDescription(dramaDto.getDescription());
        Drama savedDrama = dramaRepository.save(drama);
        return new DramaDto(savedDrama);
    }

    // TODO: Create update service to update drama's reviews

    @Override
    public DramaDto createDrama(DramaDto dramaDto) {
        Drama newDrama = new Drama();
        newDrama.setName(dramaDto.getName());
        newDrama.setDescription(dramaDto.getDescription());
        Drama savedDrama = dramaRepository.save(newDrama);
        return new DramaDto(savedDrama);
    }

    @Override
    public void deleteDrama(Long id) {
        dramaRepository.deleteById(id);
    }
    
}
