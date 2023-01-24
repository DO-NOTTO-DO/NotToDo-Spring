package sopt.nottodo.dto.mission;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import sopt.nottodo.domain.RecommendMission;

@Getter
@EqualsAndHashCode
public class RecommendMissionDto {
    private final Long id;
    private final String title;

    public RecommendMissionDto(RecommendMission recommendMission) {
        this.id = recommendMission.getId();
        this.title = recommendMission.getTitle();
    }
}
