package sopt.nottodo.recommend.dto;

import lombok.Getter;
import sopt.nottodo.recommend.domain.RecommendAction;

@Getter
public class RecommendActionDto {

    private final String name;

    public RecommendActionDto(RecommendAction action) {
        this.name = action.getName();
    }
}
