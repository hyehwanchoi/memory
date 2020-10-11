package com.date.memory.web;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import com.date.memory.domain.memory.Memories;
import com.date.memory.domain.memory.MemoriesRepository;
import com.date.memory.web.dto.MemoriesSaveRequestDto;
import com.date.memory.web.dto.MemoriesUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MemoriesApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @Autowired
    private MemoriesRepository memoriesRepository;

    @AfterEach
    public void tearDown() throws Exception {
        memoriesRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles = "USER")
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
        mvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());
        
        List<Memories> all = memoriesRepository.findAll();
        assertEquals(all.get(0).getPlace(), "콩지Pot지");
        assertEquals(all.get(0).getAddress(), "서울시 종로구");
    }

    @Test
    @WithMockUser(roles = "USER")
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
        mvc.perform(put(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(new ObjectMapper().writeValueAsString(requestDto)))
            .andExpect(status().isOk());
        
        List<Memories> all = memoriesRepository.findAll();
        assertEquals(all.get(0).getAddress(), expectedAddress);
        assertEquals(all.get(0).getExplanation(), expectedExplanation);
    }
}
    