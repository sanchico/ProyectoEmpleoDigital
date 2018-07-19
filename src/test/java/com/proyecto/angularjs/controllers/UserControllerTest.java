package com.proyecto.angularjs.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

import com.proyecto.angularjs.models.User;
import com.proyecto.angularjs.services.UserService;
import com.proyecto.angularjs.utils.UserHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @Before
    public void setUp() throws Exception {
        userController = new UserController(userService);
    }

    @Test
    public void shouldListAllUsers() throws Exception {
        stubServiceToReturnExistingUsers(10);
        ResponseEntity<List<User>> response = userController.listAllUsers();
        assertNotNull(response);
        assertEquals(10, response.getBody().size());
        // verify user was passed to UserService
        verify(userService, times(1)).findAllUsers();
    }

    @Test
    public void shouldListAllUsersEmpty() throws Exception {
        stubServiceToReturnExistingUsers(0);
        ResponseEntity<List<User>> response = userController.listAllUsers();
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // verify user was passed to UserService
        verify(userService, times(1)).findAllUsers();
    }


    @Test
    public void shouldGetUser() throws Exception {
        stubServiceToReturnExistingUser();
        ResponseEntity<?> response = userController.getUser(1l);
        assertNotNull(response);
        assertNotNull(response.getBody());
        // verify user was passed to UserService
        verify(userService, times(1)).findById(any(Long.class));
    }

    @Test
    public void shouldGetUserNotExisting() throws Exception {
        stubServiceToReturnNotExistingUser();
        ResponseEntity<?> response = userController.getUser(1l);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        // verify user was passed to UserService
        verify(userService, times(1)).findById(any(Long.class));
    }

    @Test
    public void shouldCreateUser() throws Exception {
        final User user = UserHelper.createUser();
        stubServiceToReturnSaveUser();
        ResponseEntity<?> response = userController.createUser(user);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        // verify user was passed to UserService
        verify(userService, times(1)).saveUser(any(User.class));
    }

    @Test
    public void shouldCreateUserExisting() throws Exception {
        final User user = UserHelper.createUser();
        stubServiceToReturnSaveUserExisting();
        ResponseEntity<?> response = userController.createUser(user);
        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        // verify user wasn't passed to UserService
        verify(userService, times(0)).saveUser(any(User.class));
    }

    @Test
    public void shouldUpdateUser() throws Exception {
        final User user = UserHelper.createUser();
        stubServiceToReturnUpdateUser();
        stubServiceToReturnExistingUser();
        ResponseEntity<?> response = userController.updateUser(1, user);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        // verify user was passed to UserService
        verify(userService, times(1)).updateUser(any(User.class));
    }

    @Test
    public void shouldUpdateUserNotExisiting() throws Exception {
        final User user = UserHelper.createUser();
        stubServiceToReturnNotExistingUser();
        ResponseEntity<?> response = userController.updateUser(1, user);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        ResponseEntity<?> response2 = userController.createUser(user);
        // verify user wasn't passed to UserService
        verify(userService, times(0)).updateUser(any(User.class));
    }

    @Test
    public void shouldDeleteUser() throws Exception {
        stubServiceToReturnExistingUser();
        ResponseEntity<?> response = userController.deleteUser(1l);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        // verify user was passed to UserService
        verify(userService, times(1)).deleteUserById(any(Long.class));
    }

    @Test
    public void shouldDeleteUserNotExisiting() throws Exception {
        stubServiceToReturnNotExistingUser();
        ResponseEntity<?> response = userController.deleteUser(1l);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        // verify user wasn't passed to UserService
        verify(userService, times(0)).deleteUserById(any(Long.class));
    }

    private void stubServiceToReturnExistingUsers(int howMany) {
        when(userService.findAllUsers()).thenReturn(UserHelper.createUserList(howMany));
    }

    private void stubServiceToReturnExistingUser() {
        when(userService.findById(any(Long.class))).thenReturn(UserHelper.createUserList(1).get(0));
    }

    private void stubServiceToReturnNotExistingUser() {
        when(userService.findById(any(Long.class))).thenReturn(null);
    }

    private void stubServiceToReturnSaveUser() {
        final User user = UserHelper.createUser();
        when(userService.saveUser(any(User.class))).thenReturn(user);
    }

    private void stubServiceToReturnSaveUserExisting() {
        when(userService.isUserExist(any(User.class))).thenReturn(true);
    }

    private void stubServiceToReturnUpdateUser() {
        final User user = UserHelper.createUser();
        when(userService.updateUser(any(User.class))).thenReturn(user);
    }
}