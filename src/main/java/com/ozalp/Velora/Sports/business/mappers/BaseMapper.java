package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.entities.abstracts.BaseEntity;

public interface BaseMapper<T extends BaseEntity, R, W> {

    T toEntity(R request);

    W toResponse(T entity);


}
