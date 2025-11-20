package com.ozalp.Velora.Sports.business.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskFeedResponse {

    private List<WorkoutItemResponse> workoutItems;

}
