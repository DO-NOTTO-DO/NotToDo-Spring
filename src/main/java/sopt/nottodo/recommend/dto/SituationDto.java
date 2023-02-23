package sopt.nottodo.recommend.dto;

import lombok.Getter;

@Getter
public class SituationDto {

    private final String name;

    public SituationDto(String name) {
        this.name = name;
    }
}
