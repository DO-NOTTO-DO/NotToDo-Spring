package sopt.nottodo.service;

import sopt.nottodo.dto.environment.CategoryDto;
import sopt.nottodo.dto.mission.RecommendMissionResponse;

import java.util.List;

public interface EnvironmentService {

    List<CategoryDto> getCategory();
    List<RecommendMissionResponse> getMissionByCategory(Long recommendCategoryId);
}
