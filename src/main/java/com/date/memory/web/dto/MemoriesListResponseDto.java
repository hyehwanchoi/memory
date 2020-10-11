package com.date.memory.web.dto;

import java.time.LocalDateTime;

import com.date.memory.domain.memory.Memories;

import lombok.Getter;

@Getter
public class MemoriesListResponseDto {
    
    private Long id;
    private String kinds;
    private String place;
    private String address;
    private String explanation;
    private LocalDateTime modifiedDate;

    public MemoriesListResponseDto(Memories entity) {
        this.id = entity.getId();
        this.kinds = entity.getKinds();
        this.place = entity.getPlace();
        this.address = entity.getAddress();
        this.explanation = entity.getExplanation();
        this.modifiedDate = entity.getModifiedDate();
    }
}
