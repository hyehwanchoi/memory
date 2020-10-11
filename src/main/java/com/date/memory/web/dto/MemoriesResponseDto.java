package com.date.memory.web.dto;

import com.date.memory.domain.memory.Memories;

import lombok.Getter;

@Getter
public class MemoriesResponseDto {
    
    private Long id;
    private String kinds;
    private String place;
    private String address;
    private String explanation;

    public MemoriesResponseDto(Memories entity) {
        this.id = entity.getId();
        this.kinds = entity.getKinds();
        this.place = entity.getPlace();
        this.address = entity.getAddress();
        this.explanation = entity.getExplanation();
    }
}
