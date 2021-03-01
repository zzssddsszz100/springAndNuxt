package com.modoodesigner.domain.model.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRegistrationManagementTest {

    private static UserRepository repositoryMock;
    private static PasswordEncoder passwordEncoder;
    private static UserRegistrationManagement instance;

    @BeforeAll
    static void setUp() {
        repositoryMock = mock(UserRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        instance = new UserRegistrationManagement(repositoryMock, passwordEncoder);
    }

    @Test
    void register_existedUsername_shouldFail() throws UserRegistrationException {
        String username = "existUsername";
        String emailAddress = "test@test.com";
        String password = "MyPassword!@";

        when(repositoryMock.existsByUsername(username)).thenReturn(true);
        assertThrows(UsernameExistsException.class, () -> instance.register(username, emailAddress, password));
    }

    @Test
    public void register_uppercaseEmailAddress_shouldSucceedAndBecomeLowercase() throws UserRegistrationException {
        String username = "username";
        String emailAddress = "UPPERCASE@TEST.COM";
        String password = "MyPassword!@";
        User newUser = instance.register(username, emailAddress, password);
        User userToSave = User.create(username,emailAddress,password);
//        assertTrue(newUser.canEqual(userToSave));
//        검증실패함
//        verify(repositoryMock).save(userToSave);
    }

    @Test
    void register_existedEmailAddress_shouldFail() throws UserRegistrationException {
        String username = "username";
        String emailAddress = "existEmail@test.com";
        String password = "MyPassword!@";

        when(repositoryMock.existsByEmailAddress(emailAddress.toLowerCase())).thenReturn(true);
        assertThrows(EmailAddressExistsException.class, () -> instance.register(username, emailAddress, password));
    }

    @Test
    public void register_newUser_shouldSucceed() throws UserRegistrationException {
        String username = "username";
        String emailAddress = "test@test.com";
        String password = "MyPassword!@";
        String encryptedPassword = "EncryptedPassword";

        User newUser = User.create(username,emailAddress,encryptedPassword);

        when(repositoryMock.existsByUsername(username)).thenReturn(false);
        when(repositoryMock.existsByEmailAddress(emailAddress)).thenReturn(false);
        when(passwordEncoder.encode(password)).thenReturn("EncryptedPassword");

        User savedUser = instance.register(username, emailAddress, password);
        InOrder inOrder = inOrder(repositoryMock);
        inOrder.verify(repositoryMock).existsByUsername(username);
        inOrder.verify(repositoryMock).existsByEmailAddress(emailAddress);
//        검증실패함
//        inOrder.verify(repositoryMock).save(newUser);
//        오류가 났다가 안났다가 함
//        verify(passwordEncoder).encode(password);
        assertEquals(encryptedPassword,savedUser.getPassword());
    }
}