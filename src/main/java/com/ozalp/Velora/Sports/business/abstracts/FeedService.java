package com.ozalp.Velora.Sports.business.abstracts;

import com.ozalp.Velora.Sports.business.dtos.responses.HomeFeedResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.ProfileResponse;
import com.ozalp.Velora.Sports.business.dtos.responses.TaskFeedResponse;

import java.util.UUID;

public interface FeedService {

    HomeFeedResponse getHomeFeed(UUID athleteId);

    TaskFeedResponse getTaskFeed(UUID athleteId);

    ProfileResponse getProfileFeed(UUID athleteId);

    Object getStaticsFeed(UUID organizationId);
}
