package com.date.memory.domain.memory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.date.memory.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Memories extends BaseTimeEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String kinds; //데이트 종류

    @Column(columnDefinition = "TEXT", nullable = false)
    private String place; //장소명

    @Column(columnDefinition = "TEXT", nullable = false)
    private String address; //장소 주소

    @Column(columnDefinition = "TEXT")
    private String explanation; //설명

    @Builder
    public Memories(String kinds, String place, String address, String explanation) {
        this.kinds = kinds;
        this.place = place;
        this.address = address;
        this.explanation = explanation;
    }

    public void update(String kinds, String place, String address, String explanation) {
        this.kinds = kinds;
        this.place = place;
        this.address = address;
        this.explanation = explanation;
    }
}