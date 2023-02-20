package sopt.nottodo.mission.dto;

import lombok.Data;

@Data
public class DailyMissionPercentageDto {

    private final String actionDate;
    private final float percentage;

    public DailyMissionPercentageDto(String actionDate, float percentage) {
        this.actionDate = actionDate;
        this.percentage = percentage;
    }
}
