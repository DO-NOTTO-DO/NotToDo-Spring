package sopt.nottodo.mission.dto;

import lombok.Data;
import sopt.nottodo.mission.domain.CompletionStatus;

import java.util.Date;

@Data
public class MissionCompletionStatusDto {

    private final Date actionDate;
    private final CompletionStatus completionStatus;
}
