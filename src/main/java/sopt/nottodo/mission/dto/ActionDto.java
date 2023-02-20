package sopt.nottodo.mission.dto;

import lombok.Getter;
import sopt.nottodo.mission.domain.Action;

@Getter
public class ActionDto {

    private final String name;

    public ActionDto(Action action) {
        this.name = action.getName();
    }
}
