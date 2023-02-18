package sopt.nottodo.service;

import sopt.nottodo.dto.mission.DailyMissionPercentageDto;
import sopt.nottodo.dto.mission.DailyMissionDto;
import sopt.nottodo.dto.mission.MissionTitleDto;

import java.util.List;

public interface MissionService {

    List<DailyMissionDto> getDailyMission(String today, Long userId);
    List<DailyMissionPercentageDto> getWeeklyMissionPercentage(String startDate, Long userId);
    List<MissionTitleDto> getRecentMissions(Long userId);
}
