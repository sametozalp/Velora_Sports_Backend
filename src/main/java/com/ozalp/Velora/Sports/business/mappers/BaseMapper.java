package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.business.dtos.requests.BaseRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.BaseResponse;
import com.ozalp.Velora.Sports.entities.abstracts.BaseEntity;

public interface BaseMapper<T extends BaseEntity, R extends BaseRequest, W extends BaseResponse> {

    T toEntity(R request);

    W toResponse(T entity);


}
