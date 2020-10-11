package com.date.memory.domain.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemoriesRepositoryTest {
    
    @Autowired
    MemoriesRepository memoriesRepository;

    @AfterEach
    public void clean() {
        memoriesRepository.deleteAll();
    }

    @Test
    public void memoryList() {
        String place = "문화식당";
        String address = "성수동";

        memoriesRepository.save(Memories.builder()
                                .place(place)
                                .address(address)
                                .build());

        List<Memories> memoryList = memoriesRepository.findAll();

        Memories memory = memoryList.get(0);
        assertEquals(memory.getPlace(), place);
        assertEquals(memory.getAddress(), address);
    }

    @Test
    public void BaseTimeEntity_save() {
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        memoriesRepository.save(Memories.builder()
                                    .kinds("종류")
                                    .place("장소명")
                                    .address("주소")
                                    .explanation("설명")
                                    .build());

        List<Memories> memoriesList = memoriesRepository.findAll();
        Memories memories = memoriesList.get(0);

        System.out.println(">>>>>>> createdDate:"+memories.getCreatedDate()+",,,modifiedDate:"+memories.getModifiedDate());

        assert(memories.getCreatedDate().isAfter(now));
        assert(memories.getModifiedDate().isAfter(now));
    }
}
    