package com.musalasoft.drone.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.musalasoft.drone.application.dto.DroneRequest;
import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.application.service.DroneService;
import com.musalasoft.drone.domain.model.DroneModel;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DroneController.class)
class DroneControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DroneService service;
    @MockBean
    private DroneMapper mapper;

    @Test
    @SneakyThrows
    void register_success() {

        RequestBuilder builder = MockMvcRequestBuilders
                .post("/api/v1/drones/register")
                .contentType(APPLICATION_JSON)
                .content(jsonString(droneRequest("Test_Serial_123", DroneModel.LIGHT_WEIGHT)));

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void register_badRequest_emptySerialNumber() {

        RequestBuilder builder = MockMvcRequestBuilders
                .post("/api/v1/drones/register")
                .contentType(APPLICATION_JSON)
                .content(jsonString(droneRequest("", DroneModel.LIGHT_WEIGHT)));

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @SneakyThrows
    void register_badRequest_missingDroneModel() {

        RequestBuilder builder = MockMvcRequestBuilders
                .post("/api/v1/drones/register")
                .contentType(APPLICATION_JSON)
                .content(jsonString(droneRequest(UUID.randomUUID().toString(), null)));

        mockMvc.perform(builder)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private String jsonString(Object o) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(o);
    }

    private static DroneRequest droneRequest(String serialNumber, DroneModel model) {
        var request = new DroneRequest();
        request.setSerialNumber(serialNumber);
        request.setModel(model == null ? null : model.name());
        return request;
    }
}