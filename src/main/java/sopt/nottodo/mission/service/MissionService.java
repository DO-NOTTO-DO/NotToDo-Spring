package sopt.nottodo.mission.service;

import sopt.nottodo.mission.dto.DailyMissionDTO;
import sopt.nottodo.mission.dto.DailyMissionStatusDto;
import sopt.nottodo.mission.dto.MissionTitleDto;

import java.util.List;

public interface MissionService {

    List<DailyMissionDTO> getDailyMission(String today, Long userId);

    List<DailyMissionStatusDto> getWeeklyMission(String startDate, Long userId);

    List<MissionTitleDto> getRecentMissions(Long userId);

    void deleteMission(Long missionId, Long userId);
}
