package sopt.nottodo.service;

import sopt.nottodo.domain.CompletionStatus;
import sopt.nottodo.dto.mission.DailyMissionPercentageDto;
import sopt.nottodo.dto.mission.MissionCompletionStatusDto;
import sopt.nottodo.dto.mission.MissionDto;
import sopt.nottodo.dto.mission.MissionTitleDto;

import java.util.List;

public interface MissionService {

    List<MissionDto> getDailyMission(String today, Long userId);
    List<DailyMissionPercentageDto> getWeeklyMissionPercentage(String startDate, Long userId);
    List<MissionTitleDto> getRecentMissions(Long userId);
    MissionCompletionStatusDto changeMissionCompletionStatus(Long missionId, CompletionStatus completionStatus, Long userId);
    void deleteMission(Long missionId, Long userId);
}
