package com.date.memory.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemoriesUpdateRequestDto {
    
    private String kinds;
    private String place;
    private String address;
    private String explanation;

    @Builder
    public MemoriesUpdateRequestDto(String kinds, String place, String address, String explanation) {
        this.kinds = kinds;
        this.place = place;
        this.address = address;
        this.explanation = explanation;
    }
}
