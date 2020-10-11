package com.date.memory.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.date.memory.domain.memory.Memories;
import com.date.memory.domain.memory.MemoriesRepository;
import com.date.memory.web.dto.MemoriesListResponseDto;
import com.date.memory.web.dto.MemoriesResponseDto;
import com.date.memory.web.dto.MemoriesSaveRequestDto;
import com.date.memory.web.dto.MemoriesUpdateRequestDto;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoriesService {
    
    private final MemoriesRepository memoriesRepository;

    @Transactional
    public Long save(MemoriesSaveRequestDto requestDto) {
        return memoriesRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, MemoriesUpdateRequestDto requestDto) {
        Memories memories = memoriesRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        memories.update(requestDto.getKinds(), requestDto.getPlace(), requestDto.getAddress(), requestDto.getExplanation());

        return id;
    }

    @Transactional
    public List<MemoriesListResponseDto> findAllDesc() {
        return memoriesRepository.findAllDesc().stream()
                                    .map(MemoriesListResponseDto::new)
                                    .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Memories memories = memoriesRepository.findById(id)
                                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        memoriesRepository.delete(memories);
    }

    public MemoriesResponseDto findById(Long id) {
        Memories entity = memoriesRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));

        return new MemoriesResponseDto(entity);
    }
}
