package sopt.nottodo.recommend.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.nottodo.recommend.dto.RecommendSituationDto;
import sopt.nottodo.recommend.repository.RecommendRepository;
import sopt.nottodo.recommend.service.RecommendService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendServiceImpl implements RecommendService {

    private final RecommendRepository recommendRepository;

    @Override
    public List<RecommendSituationDto> getRecommendSituation() {
        return recommendRepository.findAll().stream()
                .map(situation -> new RecommendSituationDto(situation.getName()))
                .collect(Collectors.toUnmodifiableList());
    }
}
