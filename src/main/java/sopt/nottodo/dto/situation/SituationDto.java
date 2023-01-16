package sopt.nottodo.dto.situation;

import lombok.Getter;
import sopt.nottodo.domain.RecommendSituation;
import sopt.nottodo.domain.Situation;

@Getter
public class SituationDto {
    private final String name;

    public SituationDto(Situation situation) {
        this.name = situation.getName();
    }

    public SituationDto(RecommendSituation situation) {
        this.name = situation.getName();
    }
}
