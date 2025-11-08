package com.ozalp.Velora.Sports.services;

import com.ozalp.Velora.Sports.business.abstracts.RoleService;
import com.ozalp.Velora.Sports.business.concretes.UserManager;
import com.ozalp.Velora.Sports.business.mappers.UserMapper;
import com.ozalp.Velora.Sports.dataAcess.UserRepository;
import com.ozalp.Velora.Sports.entities.concretes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserMapper mapper;
    @Mock
    private UserRepository repository;
    @Mock
    private RoleService roleService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserManager userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setFirstName("samet");
        user.setLastName("ozalp");
        user.setPassword("123456");
        user.setEmail("sametozalpbusiness@gmail.com");
        user.setUsername("sametozalp");
    }

    @Test
    void testCreate() {
        when(repository.save(user)).thenReturn(user);
        User result = userService.create(user);

        assertEquals("samet", result.getFirstName());
        verify(repository, times(1)).save(user);
    }
}
