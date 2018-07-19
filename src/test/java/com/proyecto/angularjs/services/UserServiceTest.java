package com.proyecto.angularjs.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import java.util.List;

import com.proyecto.angularjs.models.User;
import com.proyecto.angularjs.repositories.UserRepository;
import com.proyecto.angularjs.utils.UserHelper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserService(userRepository);
    }

    @Test
    public void shouldListAllUsers() throws Exception {
        stubServiceToReturnExistingUsers(10);
        List<User> response = userService.findAllUsers();
        assertNotNull(response);
        assertEquals(10, response.size());
        // verify user was passed to UserService
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void shouldGetUser() throws Exception {
        stubServiceToReturnExistingUser();
        User response = userService.findById(1l);
        assertNotNull(response);
        // verify user was passed to UserService
        verify(userRepository, times(1)).getOne(any(Long.class));
    }

    @Test
    public void shouldGetUserByName() throws Exception {
        stubServiceToReturnExistingUserByName();
        User response = userService.findByName("User");
        assertNotNull(response);
        // verify user was passed to UserService
        verify(userRepository, times(1)).findByName(any(String.class));
    }

    @Test
    public void shouldIsUserExist() throws Exception {
        stubServiceToReturnExistingUserByName();
        User user = UserHelper.createUser();
        boolean response = userService.isUserExist(user);
        assertEquals(true, response);
        // verify user was passed to UserService
        verify(userRepository, times(1)).findByName(any(String.class));
    }

    @Test
    public void shouldIsUserNotExist() throws Exception {
        stubServiceToReturnNotExistingUserByName();
        User user = UserHelper.createUser();
        boolean response = userService.isUserExist(user);
        assertEquals(false, response);
        // verify user was passed to UserService
        verify(userRepository, times(1)).findByName(any(String.class));
    }

    @Test
    public void shouldCreateUserExisting() throws Exception {
        final User user = UserHelper.createUser();
        stubServiceToReturnSaveUserExisting();
        User response = userService.saveUser(user);
        assertNotNull(response);
        // verify user was passed to UserService
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void shouldUpdateUserExisting() throws Exception {
        final User user = UserHelper.createUser();
        stubServiceToReturnSaveUserExisting();
        User response = userService.updateUser(user);
        assertNotNull(response);
        // verify user was passed to UserService
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void shouldDeleteUserExisting() throws Exception {
        userService.deleteUserById(1l);
        // verify user was passed to UserService
        verify(userRepository, times(1)).deleteById(any(Long.class));
    }

    private void stubServiceToReturnExistingUsers(int howMany) {
        when(userRepository.findAll()).thenReturn(UserHelper.createUserList(howMany));
    }

    private void stubServiceToReturnExistingUser() {
        when(userRepository.getOne(any(Long.class))).thenReturn(UserHelper.createUser());
    }

    private void stubServiceToReturnExistingUserByName() {
        when(userRepository.findByName(any(String.class))).thenReturn(UserHelper.createUser());
    }

    private void stubServiceToReturnNotExistingUserByName() {
        when(userRepository.findByName(any(String.class))).thenReturn(null);
    }

    private void stubServiceToReturnSaveUserExisting() {
        when(userRepository.save(any(User.class))).thenReturn(UserHelper.createUser());
    }
}