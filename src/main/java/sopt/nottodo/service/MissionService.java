package sopt.nottodo.service;

import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.dto.mission.DailyMissionResponse;

public interface MissionService {

    @Transactional
    DailyMissionResponse getDailyMission(String today, Long userId);
}
