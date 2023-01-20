package sopt.nottodo.service;

import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.dto.mission.DailyMissionPercentageDto;
import sopt.nottodo.dto.mission.MissionDto;
import sopt.nottodo.dto.mission.MissionTitleDto;

import java.util.List;

public interface MissionService {

    @Transactional
    List<MissionDto> getDailyMission(String today, Long userId);
    List<DailyMissionPercentageDto> getWeeklyMissionPercentage(String startDate, Long userId);
    List<MissionTitleDto> getRecentMissions(Long userId);
}
