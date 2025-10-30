package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.responses.HomeFeedResponse;

import java.util.UUID;

public interface FeedService {

    HomeFeedResponse getHomeFeed(UUID athleteId);
}
