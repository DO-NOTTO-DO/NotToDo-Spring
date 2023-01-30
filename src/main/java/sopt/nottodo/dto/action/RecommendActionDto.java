package sopt.nottodo.dto.action;

import lombok.Getter;
import sopt.nottodo.domain.RecommendAction;

@Getter
public class RecommendActionDto {

    private final String name;

    public RecommendActionDto(RecommendAction action) {
        this.name = action.getName();
    }
}