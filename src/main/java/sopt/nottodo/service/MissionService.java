package sopt.nottodo.service;

import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.dto.mission.MissionDto;

import java.util.List;

public interface MissionService {

    @Transactional
    List<MissionDto> getDailyMission(String today, Long userId);
}
