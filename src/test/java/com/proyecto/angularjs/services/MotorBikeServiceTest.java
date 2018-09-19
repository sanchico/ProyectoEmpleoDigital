package com.proyecto.angularjs.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

import com.proyecto.angularjs.models.MotorBike;
import com.proyecto.angularjs.repositories.MotorBikeRepository;
import com.proyecto.angularjs.utils.MotorBikeHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MotorBikeServiceTest {

    @Mock
    private MotorBikeRepository motorBikeRepository;

    private MotorBikeService motorBikeService;

    @Before
    public void setUp() throws Exception {
    	motorBikeService = new MotorBikeService(motorBikeRepository);
    }

    @Test
    public void shouldListAllMotorBikes() throws Exception {
        stubServiceToReturnExistingMotorBikes(10);
        List<MotorBike> response = motorBikeService.findAllMotorBikes();
        assertNotNull(response);
        assertEquals(10, response.size());
        // verify motorBike was passed to MotorBikeService
        verify(motorBikeRepository, times(1)).findAll();
    }

    @Test
    public void shouldGetMotorBike() throws Exception {
        stubServiceToReturnExistingMotorBike();
        MotorBike response = motorBikeService.findById(1l);
        assertNotNull(response);
        // verify motorBike was passed to MotorBikeService
        verify(motorBikeRepository, times(1)).getOne(any(Long.class));
    }

    @Test
    public void shouldGetMotorBikeByName() throws Exception {
        stubServiceToReturnExistingMotorBikeByNumber();
        MotorBike response = motorBikeService.findByNumber("moto");
        assertNotNull(response);
        // verify user was passed to UserService
        verify(motorBikeRepository, times(1)).findByNumber(any(String.class));
    }

    @Test
    public void shouldIsMotorBikeExist() throws Exception {
        stubServiceToReturnExistingMotorBikeByNumber();
        MotorBike motorBike = MotorBikeHelper.createMotorBike();
        boolean response = motorBikeService.isMotorBikeExist(motorBike);
        assertEquals(true, response);
        // verify user was passed to UserService
        verify(motorBikeRepository, times(1)).findByNumber(any(String.class));
    }

    @Test
    public void shouldIsMotorBikeNotExist() throws Exception {
        stubServiceToReturnNotExistingMotorBikeByNumber();
        MotorBike motorBike = MotorBikeHelper.createMotorBike();
        boolean response = motorBikeService.isMotorBikeExist(motorBike);
        assertEquals(false, response);
        // verify user was passed to UserService
        verify(motorBikeRepository, times(1)).findByNumber(any(String.class));
    }

    @Test
    public void shouldCreateMotorBikeExisting() throws Exception {
        final MotorBike motorBike = MotorBikeHelper.createMotorBike();
        stubServiceToReturnSaveMotorBikeExisting();
        MotorBike response = motorBikeService.saveMotorBike(motorBike);
        assertNotNull(response);
        // verify user was passed to UserService
        verify(motorBikeRepository, times(1)).save(any(MotorBike.class));
    }

    @Test
    public void shouldUpdateMotorBikeExisting() throws Exception {
        final MotorBike motorBike = MotorBikeHelper.createMotorBike();
        stubServiceToReturnSaveMotorBikeExisting();
        MotorBike response = motorBikeService.updateMotorBike(motorBike);
        assertNotNull(response);
        // verify motorBike was passed to MotorBikeService
        verify(motorBikeRepository, times(1)).save(any(MotorBike.class));
    }

    @Test
    public void shouldDeleteMotorBikeExisting() throws Exception {
        motorBikeService.deleteMotorBikeById(1l);
        // verify motorBike was passed to MotorBikeService
        verify(motorBikeRepository, times(1)).deleteById(any(Long.class));
    }

    private void stubServiceToReturnExistingMotorBikes(int howMany) {
        when(motorBikeRepository.findAll()).thenReturn(MotorBikeHelper.createMotorBikeList(howMany));
    }

    private void stubServiceToReturnExistingMotorBike() {
        when(motorBikeRepository.getOne(any(Long.class))).thenReturn(MotorBikeHelper.createMotorBike());
    }

    private void stubServiceToReturnExistingMotorBikeByNumber() {
        when(motorBikeRepository.findByNumber(any(String.class))).thenReturn(MotorBikeHelper.createMotorBike());
    }

    private void stubServiceToReturnNotExistingMotorBikeByNumber() {
        when(motorBikeRepository.findByNumber(any(String.class))).thenReturn(null);
    }

    private void stubServiceToReturnSaveMotorBikeExisting() {
        when(motorBikeRepository.save(any(MotorBike.class))).thenReturn(MotorBikeHelper.createMotorBike());
    }
}