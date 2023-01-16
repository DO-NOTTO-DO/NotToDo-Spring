package sopt.nottodo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.nottodo.domain.User;
import sopt.nottodo.dto.situation.SituationDto;
import sopt.nottodo.dto.situation.SituationResponse;
import sopt.nottodo.repository.MissionRepository;
import sopt.nottodo.repository.RecommendSituationRepository;
import sopt.nottodo.repository.UserRepository;
import sopt.nottodo.service.SituationService;
import sopt.nottodo.util.exception.CustomException;
import sopt.nottodo.util.response.ResponseCode;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SituationServiceImpl implements SituationService {

    private final MissionRepository missionRepository;
    private final RecommendSituationRepository recommendSituationRepository;
    private final UserRepository userRepository;

    @Override
    public SituationResponse getSituations(Long userId) {
        User user = findUser(userId);
        List<SituationDto> recommendSituations = recommendSituationRepository.findFirst9AllByOrderByIdDesc().stream()
                .map(SituationDto::new)
                .collect(Collectors.toUnmodifiableList());
        List<SituationDto> recentSituations = missionRepository.findByUser(user).stream()
                .map((mission) -> {
                    return new SituationDto(mission.getSituation());
                }).collect(Collectors.toUnmodifiableList());
        return new SituationResponse(recommendSituations, recentSituations);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ResponseCode.USER_NOT_FOUND)
        );
    }
}
