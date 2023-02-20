package sopt.nottodo.mission.dto;

import sopt.nottodo.mission.domain.CompletionStatus;
import sopt.nottodo.mission.domain.DailyMission;

public class DailyMissionDTO {

    private final Long id;
    private final String title;
    private final String situationName;
    private final CompletionStatus completionStatus;

    public DailyMissionDTO(DailyMission dailyMission) {
        this.id = dailyMission.getMission().getId();
        this.title = dailyMission.getMission().getTitle();
        this.situationName = dailyMission.getMission().getSituation().getName();
        this.completionStatus = dailyMission.getCompletionStatus();
    }
}
