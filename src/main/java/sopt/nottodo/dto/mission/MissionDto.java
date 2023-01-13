package sopt.nottodo.dto.mission;

import lombok.Getter;
import sopt.nottodo.domain.Action;
import sopt.nottodo.domain.CompletionStatus;
import sopt.nottodo.domain.Mission;
import sopt.nottodo.dto.action.ActionDto;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MissionDto {

    private final Long id;
    private final String title;
    private final CompletionStatus completionStatus;
    private final String goal;
    private final List<ActionDto> actions;

    public MissionDto(Mission mission) {
        this.id = mission.getId();
        this.title = mission.getNotTodo().getTitle();
        this.completionStatus = mission.getCompletionStatus();
        this.goal = mission.getGoal();
        this.actions = new ArrayList<>();
        makeActionDtos(mission.getActions());
    }

    private void makeActionDtos(List<Action> actions) {
        actions.forEach(action -> this.actions.add(new ActionDto(action)));
    }
}
