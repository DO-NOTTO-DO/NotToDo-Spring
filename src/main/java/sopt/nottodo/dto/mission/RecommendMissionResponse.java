package sopt.nottodo.dto.mission;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import sopt.nottodo.domain.RecommendAction;
import sopt.nottodo.dto.action.RecommendActionDto;


import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
public class RecommendMissionResponse {

    private final String title;
    private final List<RecommendActionDto> recommendActions;

    public RecommendMissionResponse(String title, List<RecommendAction> actions) {
        this.title = title;
        this.recommendActions = new ArrayList<>();
        makeActionDtos(actions);
    }

    private void makeActionDtos(List<RecommendAction> actions) {
        actions.forEach(action -> this.recommendActions.add(new RecommendActionDto(action)));
    }
}