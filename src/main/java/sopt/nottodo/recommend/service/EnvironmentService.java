package sopt.nottodo.recommend.service;

import sopt.nottodo.mission.dto.RecommendMissionResponse;

import java.util.List;

public interface EnvironmentService {

//    List<CategoryDto> getCategory();
    List<RecommendMissionResponse> getMissionByCategory(Long recommendCategoryId);
}
