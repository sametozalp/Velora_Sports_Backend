package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.entities.concretes.RefreshToken;

import java.util.UUID;

public interface RefreshTokenService extends BaseService<RefreshToken> {

    void deleteUserRefreshTokens(UUID userId);

    RefreshToken findByRefreshToken(String refreshToken);
}
