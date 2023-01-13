package sopt.nottodo.dto.mission;

import lombok.Data;
import sopt.nottodo.domain.Mission;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class DailyMissionResponse {

    @NotNull
    private final List<MissionDto> missions;

    public DailyMissionResponse(List<Mission> missions) {
        this.missions = missions.stream()
                .map(MissionDto::new)
                .collect(Collectors.toUnmodifiableList());
    }
}
