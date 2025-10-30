package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HomeFeedResponse {

    private CreateUserResponse user;

    private List<CreateWorkoutProgramResponse> workoutPrograms;

    private List<CreateAthleteProgressResponse> athleteProgresses;

}
