package sopt.nottodo.mission.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import sopt.nottodo.mission.domain.Mission;

@Getter
@EqualsAndHashCode(of = {"title"})
public class MissionTitleDto {

    private final String title;

    public MissionTitleDto(Mission mission) {
        this.title = mission.getTitle();
    }
}
