package com.date.memory.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.date.memory.domain.memory.Memories;
import com.date.memory.domain.memory.MemoriesRepository;
import com.date.memory.web.dto.MemoriesSaveRequestDto;
import com.date.memory.web.dto.MemoriesUpdateRequestDto;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemoriesApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MemoriesRepository memoriesRepository;

    @AfterEach
    public void tearDown() throws Exception {
        memoriesRepository.deleteAll();
    }

    @Test
    public void Memories_save() throws Exception {
        String place = "콩지Pot지";
        String address = "서울시 종로구";

        MemoriesSaveRequestDto requestDto = MemoriesSaveRequestDto.builder()
                                                .kinds("맛집")
                                                .place(place)
                                                .address(address)
                                                .explanation("레스토랑")
                                                .build();

        String url = "http://localhost:"+ port + "/api/v1/memories";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        List<Memories> all = memoriesRepository.findAll();
        assertEquals(all.get(0).getPlace(), "콩지Pot지");
        assertEquals(all.get(0).getAddress(), "서울시 종로구");
    }

    @Test
    public void Memories_update() throws Exception {
        Memories savedMemories = memoriesRepository.save(Memories.builder() 
                                    .kinds("맛집")
                                    .place("콩지Pot지")
                                    .address("서울시 종로구")
                                    .explanation("파스타 맛집")
                                    .build());
                        
        Long updateId = savedMemories.getId();
        String expectedKinds = "볼거리";
        String expectedPlace = "쌈지길";
        String expectedAddress = "서울시 종로구 인사동";
        String expectedExplanation = "여러 볼거리";

        MemoriesUpdateRequestDto requestDto = MemoriesUpdateRequestDto.builder()
                                                .kinds(expectedKinds)
                                                .place(expectedPlace)
                                                .address(expectedAddress)
                                                .explanation(expectedExplanation)
                                                .build();
        String url = "http://localhost:"+ port + "/api/v1/memories/"+ updateId;

        HttpEntity<MemoriesUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);

        List<Memories> all = memoriesRepository.findAll();
        assertEquals(all.get(0).getKinds(), expectedKinds);
        assertEquals(all.get(0).getPlace(), expectedPlace);
        assertEquals(all.get(0).getAddress(), expectedAddress);
        assertEquals(all.get(0).getExplanation(), expectedExplanation);
    }
}
    