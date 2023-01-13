package sopt.nottodo.dto.action;

import lombok.Data;
import lombok.Getter;
import sopt.nottodo.domain.Action;

@Getter
public class ActionDto {

    private final String name;

    public ActionDto(Action action) {
        this.name = action.getName();
    }
}
