package com.proyecto.angularjs.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import java.util.List;
import com.proyecto.angularjs.models.MotorBike;
import com.proyecto.angularjs.services.MotorBikeService;
import com.proyecto.angularjs.utils.MotorBikeHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class MotorBikeControllerTest {

    @Mock
    private MotorBikeService motorBikeService;

    private MotorBikeController motorBikeController;

    @Before
    public void setUp() throws Exception {
    	motorBikeController = new MotorBikeController(motorBikeService);
    }

    @Test
    public void shouldListAllMotorBikes() throws Exception {
        stubServiceToReturnExistingMotorBikes(10);
        ResponseEntity<List<MotorBike>> response = motorBikeController.listAllMotorBikes();
        assertNotNull(response);
        assertEquals(10, response.getBody().size());
        // verify user was passed to UserService
        verify(motorBikeService, times(1)).findAllMotorBikes();
    }

    @Test
    public void shouldListAllMotorBikesEmpty() throws Exception {
        stubServiceToReturnExistingMotorBikes(0);
        ResponseEntity<List<MotorBike>> response = motorBikeController.listAllMotorBikes();
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // verify user was passed to UserService
        verify(motorBikeService, times(1)).findAllMotorBikes();
    }


    @Test
    public void shouldGetUser() throws Exception {
        stubServiceToReturnExistingMotorBike();
        ResponseEntity<?> response = motorBikeController.getMotorBike(1l);
        assertNotNull(response);
        assertNotNull(response.getBody());
        // verify user was passed to UserService
        verify(motorBikeService, times(1)).findById(any(Long.class));
    }

    @Test
    public void shouldGetMotorBikeNotExisting() throws Exception {
        stubServiceToReturnNotExistingMotorBike();
        ResponseEntity<?> response = motorBikeController.getMotorBike(1l);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        // verify user was passed to UserService
        verify(motorBikeService, times(1)).findById(any(Long.class));
    }

    @Test
    public void shouldCreateMotorBike() throws Exception {
        final MotorBike motorBike = MotorBikeHelper.createMotorBike();
        stubServiceToReturnSaveMotorBike();
        ResponseEntity<?> response = motorBikeController.createMotorBike(motorBike);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // verify user was passed to UserService
        verify(motorBikeService, times(1)).saveMotorBike(any(MotorBike.class));
    }

    @Test
    public void shouldCreateUserExisting() throws Exception {
    	final MotorBike motorBike = MotorBikeHelper.createMotorBike();
        stubServiceToReturnSaveMotorBikeExisting();
        ResponseEntity<?> response = motorBikeController.createMotorBike(motorBike);
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        // verify user wasn't passed to UserService
        verify(motorBikeService, times(0)).saveMotorBike(any(MotorBike.class));
    }

    @Test
    public void shouldUpdateMotorBike() throws Exception {
    	final MotorBike motorBike = MotorBikeHelper.createMotorBike();
        stubServiceToReturnUpdateMotorBike();
        stubServiceToReturnExistingMotorBike();
        ResponseEntity<?> response = motorBikeController.updateMotorBike(1, motorBike);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // verify user was passed to UserService
        verify(motorBikeService, times(1)).updateMotorBike(any(MotorBike.class));
    }

    @Test
    public void shouldUpdateUserNotExisiting() throws Exception {
    	final MotorBike motorBike = MotorBikeHelper.createMotorBike();
        stubServiceToReturnNotExistingMotorBike();
        ResponseEntity<?> response = motorBikeController.updateMotorBike(1, motorBike);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ResponseEntity<?> response2 = motorBikeController.createMotorBike(motorBike);
        // verify user wasn't passed to UserService
        verify(motorBikeService, times(0)).updateMotorBike(any(MotorBike.class));
    }

    @Test
    public void shouldDeleteMotorBike() throws Exception {
        stubServiceToReturnExistingMotorBike();
        ResponseEntity<?> response = motorBikeController.deleteMotorBike(1);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // verify motorBike was passed to MotorBikeService
        verify(motorBikeService, times(1)).deleteMotorBikeById(any(Long.class));
    }

    @Test
    public void shouldDeleteMotorBikeNotExisiting() throws Exception {
        stubServiceToReturnNotExistingMotorBike();
        ResponseEntity<?> response = motorBikeController.deleteMotorBike(1);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        // verify motorBike wasn't passed to UserService
        verify(motorBikeService, times(0)).deleteMotorBikeById(any(Long.class));
    }

    private void stubServiceToReturnExistingMotorBikes(int howMany) {
        when(motorBikeService.findAllMotorBikes()).thenReturn(MotorBikeHelper.createMotorBikeList(howMany));
    }

    private void stubServiceToReturnExistingMotorBike() {
        when(motorBikeService.findById(any(Long.class))).thenReturn(MotorBikeHelper.createMotorBikeList(1).get(0));
    }

    private void stubServiceToReturnNotExistingMotorBike() {
        when(motorBikeService.findById(any(Long.class))).thenReturn(null);
    }

    private void stubServiceToReturnSaveMotorBike() {
        final MotorBike motorBike = MotorBikeHelper.createMotorBike();
        when(motorBikeService.saveMotorBike(any(MotorBike.class))).thenReturn(motorBike);
    }

    private void stubServiceToReturnSaveMotorBikeExisting() {
        when(motorBikeService.isMotorBikeExist(any(MotorBike.class))).thenReturn(true);
    }

    private void stubServiceToReturnUpdateMotorBike() {
        final MotorBike motorBike = MotorBikeHelper.createMotorBike();
        when(motorBikeService.updateMotorBike(any(MotorBike.class))).thenReturn(motorBike);
    }
}