package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.requests.CreateMembershipRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateMembershipResponse;
import com.ozalp.Velora.Sports.entities.concretes.Membership;

public interface MembershipService extends BaseService<Membership> {

    CreateMembershipResponse create(CreateMembershipRequest request);

}
