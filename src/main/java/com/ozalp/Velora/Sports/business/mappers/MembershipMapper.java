package com.ozalp.Velora.Sports.business.mappers;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateMembershipRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.MembershipResponse;
import com.ozalp.Velora.Sports.entities.concretes.Membership;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MembershipMapper extends BaseMapper<Membership, CreateMembershipRequest, MembershipResponse> {
}
