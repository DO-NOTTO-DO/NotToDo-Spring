package sopt.nottodo.dto.mission;

import lombok.Getter;
import sopt.nottodo.domain.Mission;

@Getter
public class MissionTitleDto {

    private final String title;

    public MissionTitleDto(Mission mission) {
        this.title = mission.getNotTodo().getTitle();
    }
}
