package com.musalasoft.drone.application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class DroneApplication implements CommandLineRunner {

    private final SampleDroneLoader droneLoader;

    public DroneApplication(SampleDroneLoader droneLoader) {
        this.droneLoader = droneLoader;
    }

    public static void main(String[] args) {
        SpringApplication.run(DroneApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        droneLoader.load();
    }
}
