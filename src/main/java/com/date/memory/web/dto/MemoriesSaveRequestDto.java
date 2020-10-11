package com.date.memory.web.dto;

import com.date.memory.domain.memory.Memories;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoriesSaveRequestDto {
    
    private String kinds;
    private String place;
    private String address;
    private String explanation;

    @Builder
    public MemoriesSaveRequestDto(String kinds, String place, String address, String explanation) {
        this.kinds = kinds;
        this.place = place;
        this.address = address;
        this.explanation = explanation;
    }

    public Memories toEntity() {
        return Memories.builder()
                    .kinds(kinds)
                    .place(place)
                    .address(address)
                    .explanation(explanation)
                    .build();
    }
}
