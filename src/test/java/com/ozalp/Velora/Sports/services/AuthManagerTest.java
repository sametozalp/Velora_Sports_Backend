package com.ozalp.Velora.Sports.services;

import com.ozalp.Velora.Sports.business.abstracts.RefreshTokenService;
import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.business.concretes.AuthManager;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateUserRequest;
import com.ozalp.Velora.Sports.business.dtos.requests.LoginRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateUserResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.RefreshTokenResponse;
import com.ozalp.Velora.Sports.business.mappers.UserMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.common.security.JwtService;
import com.ozalp.Velora.Sports.entities.concretes.RefreshToken;
import com.ozalp.Velora.Sports.entities.concretes.User;
import com.ozalp.Velora.Sports.exceptions.errors.NotMatchedException;
import org.antlr.v4.runtime.misc.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthManagerTest {

    @Mock
    private UserService userService;

    @Mock
    private JwtService jwtService;

    @Mock
    private RefreshTokenService refreshTokenService; // ekle mock

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private AuthManager authManager;

    private User mockUser;
    private RefreshToken mockRefreshToken;

    @BeforeEach
    public void setUp() {
        mockUser = new User();
        mockUser.setUsername("sametozalp");
        mockUser.setEmail("sametozalp@gmail.com");
        mockUser.setPassword("123456");

        mockRefreshToken = new RefreshToken();
        mockRefreshToken.setRefreshToken("refresh123");
        mockRefreshToken.setUser(mockUser);

    }

    @Test
    public void register() {
        CreateUserRequest request = new CreateUserRequest();
        CreateUserResponse response = new CreateUserResponse();
        UUID uuid = UUID.randomUUID();
        response.setId(uuid);

        when(userService.create(request)).thenReturn(response);
        when(userService.findById(uuid)).thenReturn(mockUser);
        when(jwtService.generateAccessToken(mockUser)).thenReturn("access123");
        when(jwtService.generateRefreshToken(mockUser)).thenReturn(mockRefreshToken);

        CreateUserResponse result = authManager.register(request);

        assertEquals(uuid, result.getId());
        assertEquals("access123", result.getAccessToken());
        assertEquals("refresh123", result.getRefreshToken());
        verify(userService).create(request);
        verify(jwtService).generateAccessToken(mockUser);
        verify(jwtService).generateRefreshToken(mockUser);

    }

    @Test
    public void login_passwordsMatches() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("rawPassword");

        UUID uuid = UUID.randomUUID();

        CreateUserResponse response = new CreateUserResponse();
        response.setId(uuid);

        when(userService.findByEmail("test@example.com")).thenReturn(mockUser);
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(userMapper.toResponse(mockUser)).thenReturn(response);
        when(jwtService.generateAccessToken(mockUser)).thenReturn("access123");
        when(jwtService.generateRefreshToken(mockUser)).thenReturn(mockRefreshToken);

        CreateUserResponse result = authManager.login(request);

        assertEquals(uuid, result.getId());
        assertEquals("access123", result.getAccessToken());
        assertEquals("refresh123", result.getRefreshToken());
        verify(passwordEncoder).matches("rawPassword", mockUser.getPassword());
    }

    @Test
    void login_ShouldThrowException_WhenPasswordNotMatches() {

        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("wrongPassword");

        when(userService.findByEmail("test@example.com")).thenReturn(mockUser);
        when(passwordEncoder.matches("wrongPassword", mockUser.getPassword())).thenReturn(false);

        NotMatchedException exception = assertThrows(NotMatchedException.class,
                () -> authManager.login(request));

        assertEquals(Messages.AuthMessages.PASSWORDS_NOT_MATCHED, exception.getMessage());
        verify(passwordEncoder).matches("wrongPassword", mockUser.getPassword());
    }

    @Test
    void refreshToken_ShouldReturnNewTokens() {

        User mockUser = mock(User.class);
        when(mockUser.getId()).thenReturn(UUID.randomUUID());

        RefreshToken mockRefreshToken = mock(RefreshToken.class);
        when(mockRefreshToken.getRefreshToken()).thenReturn("refresh123");
        when(mockRefreshToken.getUser()).thenReturn(mockUser);

        String oldRefreshToken = "oldRefreshToken";

        Pair<String, RefreshToken> tokenPair = new Pair<>("newAccessToken", mockRefreshToken);
        when(jwtService.refreshAccessToken(oldRefreshToken)).thenReturn(tokenPair);

        RefreshTokenResponse result = authManager.refreshToken(oldRefreshToken);

        assertEquals("newAccessToken", result.getAccessToken());
        assertEquals("refresh123", result.getRefreshToken());
        assertEquals(mockUser.getId(), result.getUserId());

        verify(jwtService).refreshAccessToken(oldRefreshToken);
    }

}
