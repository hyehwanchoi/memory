package com.date.memory.domain.memory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemoriesRepository extends JpaRepository<Memories, Long> {
    
    @Query("SELECT m FROM Memories m ORDER BY m.id DESC")
    List<Memories> findAllDesc();
}