package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.RefreshTokenService;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.dataAcess.RefreshTokenRepository;
import com.ozalp.Velora.Sports.entities.concretes.RefreshToken;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenManager implements RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Override
    public RefreshToken create(RefreshToken refreshToken) {
        return repository.save(refreshToken);
    }

    @Override
    public RefreshToken findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.RefreshToken.NOT_FOUND));
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        return repository.save(refreshToken);
    }

    @Override
    public void deleteUserRefreshTokens(UUID userId) {
        List<RefreshToken> refreshTokens = repository.findByUserIdAndDeletedAtIsNull(userId);
        for(RefreshToken refreshToken: refreshTokens) {
            refreshToken.markAsDeleted();
            repository.save(refreshToken);
        }
    }

    @Override
    public RefreshToken findByRefreshToken(String refreshToken) {
        return repository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new EntityNotFoundException(Messages.RefreshToken.NOT_FOUND));
    }
}
