package com.proyecto.angularjs.utils;

import java.util.LinkedList;
import java.util.List;

import com.proyecto.angularjs.models.User;

public class UserHelper {

	public static List<User> createUserList(int howMany) {
        List<User> list = new LinkedList<>();
        for (int i = 0; i < howMany; i++) {
            list.add(new User("User " + i, "Password " + i, "USER"));
        }

		return list;
	}

	public static User createUser() {
        User user = new User("User 1", "Password 1", "USER");
		return user;
	}

}