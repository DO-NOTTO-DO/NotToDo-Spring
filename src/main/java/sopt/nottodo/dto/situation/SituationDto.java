package sopt.nottodo.dto.situation;

import lombok.Getter;
import sopt.nottodo.domain.RecommendSituation;
import sopt.nottodo.domain.Situation;

import java.util.Objects;

@Getter
public class SituationDto {
    private final String name;

    public SituationDto(Situation situation) {
        this.name = situation.getName();
    }

    public SituationDto(RecommendSituation situation) {
        this.name = situation.getName();
    }

    @Override
    public boolean equals(Object obj) {
        SituationDto situation = (SituationDto) obj;
        return (Objects.equals(this.name, situation.getName()));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
