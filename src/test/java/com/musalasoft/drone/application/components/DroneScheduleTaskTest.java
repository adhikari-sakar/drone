package com.musalasoft.drone.application.components;

import com.musalasoft.drone.application.repository.DroneRepository;
import com.musalasoft.drone.domain.model.Drone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static com.musalasoft.drone.domain.model.DroneState.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class DroneScheduleTaskTest {

    @Mock
    private DroneRepository repository;
    @Mock
    private Drone drone;

    private DroneScheduleTask task;

    @BeforeEach
    void setUp() {
        openMocks(this);
        task = new DroneScheduleTask(repository);

    }

    @Test
    void charge() {
        when(repository.findAllByDroneState(IDLE)).thenReturn(List.of(drone));
        when(drone.charge()).thenReturn(drone);
        task.execute();
        verify(drone).charge();
    }

    @Test
    void loading() {
        when(repository.findAllByDroneState(LOADING)).thenReturn(List.of(drone));
        when(drone.charge()).thenReturn(drone);
        task.execute();
        verify(drone).charge();
    }

    @Test
    void deliver() {
        when(repository.findAllByDroneState(LOADED)).thenReturn(List.of(drone));
        when(drone.deliver()).thenReturn(drone);
        task.execute();
        verify(drone).deliver();
    }

    @Test
    void delivered() {
        when(repository.findAllByDroneState(DELIVERING)).thenReturn(List.of(drone));
        when(drone.delivered()).thenReturn(drone);
        task.execute();
        verify(drone).delivered();
    }

    @Test
    void returning() {
        when(repository.findAllByDroneState(DELIVERED)).thenReturn(List.of(drone));
        when(drone.returnDrone()).thenReturn(drone);
        task.execute();
        verify(drone).returnDrone();
    }

    @Test
    void parking() {
        when(repository.findAllByDroneState(RETURNING)).thenReturn(List.of(drone));
        when(drone.land()).thenReturn(drone);
        task.execute();
        verify(drone).land();
    }
}