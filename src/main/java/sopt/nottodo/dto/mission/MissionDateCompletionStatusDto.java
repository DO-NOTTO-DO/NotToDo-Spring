package sopt.nottodo.dto.mission;

import lombok.Data;
import sopt.nottodo.domain.CompletionStatus;

import java.util.Date;

@Data
public class MissionDateCompletionStatusDto {

    private final Date actionDate;
    private final CompletionStatus completionStatus;
}
