package com.ozalp.Velora.Sports.business.concretes;

import com.ozalp.Velora.Sports.business.abstracts.AthleteService;
import com.ozalp.Velora.Sports.business.abstracts.CoachService;
import com.ozalp.Velora.Sports.business.abstracts.WorkoutProgramService;
import com.ozalp.Velora.Sports.business.dtos.requests.CreateWorkoutProgramRequest;
import com.ozalp.Velora.Sports.business.dtos.responses.CreateWorkoutProgramResponse;
import com.ozalp.Velora.Sports.business.mappers.WorkoutProgramMapper;
import com.ozalp.Velora.Sports.common.Messages;
import com.ozalp.Velora.Sports.dataAcess.WorkoutProgramRepository;
import com.ozalp.Velora.Sports.entities.concretes.WorkoutProgram;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class WorkoutProgramManager implements WorkoutProgramService {

    private final WorkoutProgramRepository repository;
    private final WorkoutProgramMapper mapper;
    private final AthleteService athleteService;
    private final CoachService coachService;

    @Override
    public WorkoutProgram create(WorkoutProgram workoutProgram) {
        return repository.save(workoutProgram);
    }

    @Override
    public WorkoutProgram findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Messages.WorkoutProgramMessages.NOT_FOUND));
    }

    @Override
    public WorkoutProgram save(WorkoutProgram workoutProgram) {
        return repository.save(workoutProgram);
    }

    @Override
    public CreateWorkoutProgramResponse create(CreateWorkoutProgramRequest request) {
        WorkoutProgram workoutProgram = mapper.toEntity(request);
        workoutProgram.setCoach(coachService.findById(request.getCoachId()));
        workoutProgram.setAthlete(athleteService.findById(request.getAthleteId()));
        return mapper.toResponse(repository.save(workoutProgram));
    }

    // bugün hangi programlari içerisinde sorgusu
//    @Override
//    public List<CreateWorkoutProgramResponse> findByAthleteId(UUID athleteId) {
//        return repository.findByAthleteIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(athleteId, LocalDateTime.now(), LocalDateTime.now())
//                .stream()
//                .map(mapper::toResponse).toList();
//    }
}
