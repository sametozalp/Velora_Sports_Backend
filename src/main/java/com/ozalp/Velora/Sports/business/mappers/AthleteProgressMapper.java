package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateAthleteProgressRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.AthleteProgressResponse;
import com.ozalp.Velora.Sports.entities.concretes.AthleteProgress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AthleteProgressMapper extends BaseMapper<AthleteProgress, CreateAthleteProgressRequest, AthleteProgressResponse> {
}
