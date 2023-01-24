package sopt.nottodo.service;

import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.domain.Mission;
import sopt.nottodo.domain.RecommendCategory;
import sopt.nottodo.dto.environment.CategoryDto;
import sopt.nottodo.dto.mission.MissionDto;
import sopt.nottodo.dto.mission.RecommendMissionDto;

import java.util.List;

public interface EnvironmentService {

    List<CategoryDto> getCategory();
    List<RecommendMissionDto> getMissionByCategory(Long recommendCategoryId);
}
