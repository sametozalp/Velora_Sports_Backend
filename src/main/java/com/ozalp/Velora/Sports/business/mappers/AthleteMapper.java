package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.business.dtos.responses.AthleteResponse;
import com.ozalp.Velora.Sports.entities.concretes.Athlete;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AthleteMapper {

    AthleteResponse toResponse(Athlete athlete);

}
