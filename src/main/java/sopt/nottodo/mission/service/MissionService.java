package sopt.nottodo.mission.service;

import sopt.nottodo.mission.dto.DailyMissionDTO;

import java.util.List;

public interface MissionService {

    List<DailyMissionDTO> getDailyMission(String today, Long userId);

//    List<DailyMissionDto> getDailyMission(String today, Long userId);
//    List<DailyMissionPercentageDto> getWeeklyMissionPercentage(String startDate, Long userId);
//    List<MissionTitleDto> getRecentMissions(Long userId);
}
