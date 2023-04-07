package com.example.Gifts;

import com.example.Gifts.exception.user.UserAlreadyExistsException;
import com.example.Gifts.exception.user.UserNotFoundException;
import com.example.Gifts.model.User;
import com.example.Gifts.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    private final Long ID = 1l;
    private final String NAME = "Name";
    private final String LOGIN = "Login";

    @BeforeEach
    @AfterEach
    void checkUserDataBaseEmpty() {
        assertTrue(UserService.userDataBase.isEmpty());
    }

    @Test
    void shouldGetUser() {
        UserService.userDataBase.add(new User(ID, NAME,LOGIN));

        User user = UserService.userDataBase.get(0);
        assertNotNull(user);

        assertEquals(ID, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(LOGIN, user.getLogin());

        UserService.userDataBase.remove(0);
    }

    @Test
    void shouldAddUser() {
        UserService.userDataBase.add(new User(ID, NAME,LOGIN));

        assertEquals(1, UserService.userDataBase.size());

        User user = UserService.userDataBase.get(0);

        assertEquals(ID, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(LOGIN, user.getLogin());

        UserService.userDataBase.remove(0);

    }

    @Test
    void shouldDeleteUser() {
        UserService.userDataBase.add(new User(ID, NAME,LOGIN));
        User user = UserService.userDataBase.get(0);

        assertNotNull(user);

        UserService.deleteUser(ID.intValue());

        assertEquals(0,UserService.userDataBase.size());
    }

    @Test
    void shouldUpdateUser() {
        UserService.userDataBase.add(new User(ID, NAME,LOGIN));
        User user = UserService.userDataBase.get(0);
        assertNotNull(user);

        String newName = "NewName";
        User newNameUser = new User(ID,newName, LOGIN);

        UserService.updateUser(newNameUser);
        user = UserService.userDataBase.get(0);

        assertEquals(newNameUser.getId(), user.getId());
        assertEquals(newNameUser.getLogin(), user.getLogin());
        assertEquals(newNameUser.getName(), user.getName());

        UserService.userDataBase.remove(0);
    }

    @Test
    void shouldAddThrowUserAlreadyExistException() {
        UserService.userDataBase.add(new User(ID, NAME,LOGIN));

        assertThrows(UserAlreadyExistsException.class,
                () -> UserService.addUser(new User(ID, NAME,LOGIN)),
                "shouldAddThrowUserAlreadyExistException");
        UserService.userDataBase.remove(0);

    }

    @Test
    void shouldDeleteThrowUserNotFoundException() {
        UserService.userDataBase.add(new User(ID, NAME,LOGIN));

        Long newId = 2L;

        assertThrows(UserNotFoundException.class,
                () -> UserService.deleteUser(newId.intValue()),
                "shouldDeleteThrowUserNotFoundException");

        UserService.userDataBase.remove(0);
    }

    @Test
    void shouldUpdateThrowUserNotFoundException() {
        UserService.userDataBase.add(new User(ID, NAME,LOGIN));

        Long newId = 2L;

        assertThrows(UserNotFoundException.class,
                () -> UserService.updateUser(new User(newId, NAME, LOGIN)),
                "shouldUpdateThrowUserNotFoundException");

        UserService.userDataBase.remove(0);
    }

}
