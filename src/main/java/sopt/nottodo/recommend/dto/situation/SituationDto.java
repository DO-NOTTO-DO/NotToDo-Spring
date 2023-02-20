package sopt.nottodo.recommend.dto.situation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import sopt.nottodo.recommend.domain.RecommendSituation;
import sopt.nottodo.mission.domain.Situation;

@Getter
@EqualsAndHashCode(of = {"name"})
public class SituationDto {
    private final String name;

    public SituationDto(Situation situation) {
        this.name = situation.getName();
    }

    public SituationDto(RecommendSituation situation) {
        this.name = situation.getName();
    }
}
