package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StaticsFeedResponse {

    List<DailyAthleteScore> myDailyScores;

    List<AthleteScoreSummaryResponse> scoreDetails;
}
