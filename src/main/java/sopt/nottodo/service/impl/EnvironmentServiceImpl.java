package sopt.nottodo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.nottodo.domain.RecommendCategory;
import sopt.nottodo.domain.RecommendMission;
import sopt.nottodo.dto.environment.CategoryDto;
import sopt.nottodo.dto.mission.RecommendMissionResponse;
import sopt.nottodo.repository.RecommendCategoryRepository;
import sopt.nottodo.repository.RecommendMissionRepository;
import sopt.nottodo.service.EnvironmentService;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class EnvironmentServiceImpl implements EnvironmentService {

    private final RecommendCategoryRepository recommendCategoryRepository;
    private final RecommendMissionRepository recommendMissionRepository;

    @Override
    public List<CategoryDto> getCategory() {
        List<RecommendCategory> categories = recommendCategoryRepository.findAll();
        return categories.stream()
                .map(CategoryDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<RecommendMissionResponse> getMissionByCategory(Long recommendCategoryId) {
        List<RecommendMission> missions = recommendMissionRepository.findByRecommendCategoryId(recommendCategoryId);
        return missions.stream()
                .map(mission -> new RecommendMissionResponse(mission.getTitle(), mission.getRecommendActions()))
                .collect(Collectors.toUnmodifiableList());
    }
}
