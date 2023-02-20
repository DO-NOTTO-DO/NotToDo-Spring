package sopt.nottodo.mission.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DailyMissionPercentageCalculateDto {

    private final Date actionDate;
    private final int count;
    private float point;

    public void addPoint(float point) {
        this.point += point;
    }

    public DailyMissionPercentageDto convertToResponse() {
        String actionDateString = actionDate.toString().substring(0, 10);
        return new DailyMissionPercentageDto(actionDateString, point / count);
    }
}
