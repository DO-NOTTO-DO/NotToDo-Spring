package sopt.nottodo.dto.mission;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DailyMissionResponse {

    @NotNull
    private final List<MissionDto> missions;
    @NotNull
    private final float percentage;
}
