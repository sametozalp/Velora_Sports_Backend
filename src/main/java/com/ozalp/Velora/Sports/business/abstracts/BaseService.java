package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.entities.abstracts.BaseEntity;

import java.util.UUID;

public interface BaseService<T extends BaseEntity> {

    T create(T t);

    T findById(UUID id);

    T save(T t);
}
