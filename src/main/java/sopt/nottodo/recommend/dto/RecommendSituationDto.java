package sopt.nottodo.recommend.dto;

import lombok.Getter;

@Getter
public class RecommendSituationDto {

    private final String name;

    public RecommendSituationDto(String name) {
        this.name = name;
    }
}
