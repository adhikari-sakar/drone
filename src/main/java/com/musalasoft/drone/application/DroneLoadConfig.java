package com.musalasoft.drone.application;

import com.musalasoft.drone.application.dto.NewDroneRequest;
import com.musalasoft.drone.application.mapper.DroneMapper;
import com.musalasoft.drone.application.repository.DroneRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "drones")
public class DroneLoadConfig {
    @Getter
    @Setter
    private List<NewDroneRequest> droneRequests = new ArrayList<>();

    @Bean
    public SampleDroneLoader droneLoader(DroneRepository repository, DroneMapper mapper) {
        return new SampleDroneLoader(repository, droneRequests, mapper);
    }
}
