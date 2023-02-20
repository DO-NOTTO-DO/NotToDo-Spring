package sopt.nottodo.recommend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.nottodo.domain.RecommendCategory;
import sopt.nottodo.recommend.domain.RecommendMission;
import sopt.nottodo.recommend.dto.CategoryDto;
import sopt.nottodo.mission.dto.RecommendMissionResponse;
import sopt.nottodo.recommend.repository.RecommendCategoryRepository;
import sopt.nottodo.recommend.repository.RecommendMissionRepository;
import sopt.nottodo.recommend.service.EnvironmentService;

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
