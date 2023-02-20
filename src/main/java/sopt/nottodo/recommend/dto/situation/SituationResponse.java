package sopt.nottodo.recommend.dto.situation;

import lombok.Getter;

import java.util.List;

@Getter
public class SituationResponse {

    private final List<SituationDto> recommends;
    private final List<SituationDto> recents;

    public SituationResponse(List<SituationDto> recommends, List<SituationDto> recents) {
        this.recommends = recommends;
        this.recents = recents;
    }
}
