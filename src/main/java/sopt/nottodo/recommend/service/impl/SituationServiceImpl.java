package sopt.nottodo.recommend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.nottodo.auth.domain.User;
import sopt.nottodo.recommend.dto.situation.SituationDto;
import sopt.nottodo.recommend.dto.situation.SituationResponse;
import sopt.nottodo.mission.repository.MissionRepository;
import sopt.nottodo.recommend.repository.RecommendSituationRepository;
import sopt.nottodo.auth.repository.UserRepository;
import sopt.nottodo.recommend.service.SituationService;
import sopt.nottodo.common.util.exception.CustomException;
import sopt.nottodo.common.util.response.ResponseCode;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SituationServiceImpl implements SituationService {

    private static final Integer SITUATION_LIMIT_COUNT = 9;

    private final MissionRepository missionRepository;
    private final RecommendSituationRepository recommendSituationRepository;
    private final UserRepository userRepository;

    @Override
    public SituationResponse getSituations(Long userId) {
        User user = findUser(userId);
        List<SituationDto> recommendSituations = getRecommendSituations();
        List<SituationDto> recentSituations = getRecentSituations(user, recommendSituations);
        return new SituationResponse(recommendSituations, recentSituations);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ResponseCode.USER_NOT_FOUND)
        );
    }

    private List<SituationDto> getRecommendSituations() {
        return recommendSituationRepository.findFirst9AllByOrderByIdDesc().stream()
                .map(SituationDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<SituationDto> getRecentSituations(User user, List<SituationDto> recommendSituations) {
        return missionRepository.findByUser(user).stream()
                .map(mission -> new SituationDto(mission.getSituation()))
                .filter(situation -> !recommendSituations.contains(situation))
                .distinct()
                .limit(SITUATION_LIMIT_COUNT)
                .collect(Collectors.toUnmodifiableList());
    }
}
