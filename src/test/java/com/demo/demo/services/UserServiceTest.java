package com.demo.demo.services;


import com.demo.demo.Exception.NotFoundException;
import com.demo.demo.model.User;
import com.demo.demo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;


    // region Sample Test OK and KO on com.demo.demo.services.UserService.delete
    @Test
    public void should_delete_user_by_id_success () {
        // Given
        Long inputId = 1L;

        User mockUser = Mockito.mock(User.class);
        when(repository.findByIdAndIsDeletedFalse(anyLong()))
                .thenReturn(Optional.of(mockUser));
        when(repository.save(any())).thenReturn(mockUser);

        // When
        service.delete(inputId);
        // Then
        verify(mockUser).setDeleted_at(any());
        verify(mockUser).setDeleted(true);
        verify(repository).save(mockUser);
    }


    @Test
    public void should_not_delete_user_by_id_not_found () {
        // Given
        Long inputId = 1L;

        when(repository.findByIdAndIsDeletedFalse(anyLong()))
                .thenReturn(Optional.empty());

        // When
        assertThrows(NotFoundException.class,
                () -> service.delete(inputId));
        // Then
        verify(repository,never()).save(any());
    }
    //endregion
    // region Test method protected
    @Test
    public void should_valid_password_ok () {
        // Given
        // When
        String inputPWD = "PwdTEst";
        String expectedValue = "$2a$10$r5V/aa4K3srvommdSgR46uPiD62txzeXByB4Le4OMQXh6r8Fj1i0C";
        Boolean output = service.validPassword(inputPWD, expectedValue);
        // Then
        Assertions.assertTrue(output);
    }
    // endregion
}
