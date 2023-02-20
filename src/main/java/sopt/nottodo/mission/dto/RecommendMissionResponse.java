package sopt.nottodo.mission.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import sopt.nottodo.recommend.domain.RecommendAction;
import sopt.nottodo.recommend.dto.RecommendActionDto;


import java.util.List;
import java.util.stream.Collectors;

@Getter
@EqualsAndHashCode
public class RecommendMissionResponse {

    private final String title;
    private final List<RecommendActionDto> recommendActions;

    public RecommendMissionResponse(String title, List<RecommendAction> actions) {
        this.title = title;
        this.recommendActions = makeActionDtos(actions);
    }

    private List<RecommendActionDto> makeActionDtos(List<RecommendAction> actions) {
        return actions.stream()
                .map(action -> new RecommendActionDto(action))
                .collect(Collectors.toUnmodifiableList());
    }
}
