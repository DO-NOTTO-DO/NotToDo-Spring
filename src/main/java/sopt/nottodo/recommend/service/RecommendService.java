package sopt.nottodo.recommend.service;

import sopt.nottodo.mission.dto.DailyMissionDTO;
import sopt.nottodo.recommend.dto.SituationDto;

import java.util.List;

public interface RecommendService {

    List<SituationDto> getRecommendSituation();
}
