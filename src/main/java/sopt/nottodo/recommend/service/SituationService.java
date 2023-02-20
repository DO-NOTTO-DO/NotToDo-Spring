package sopt.nottodo.recommend.service;

import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.recommend.dto.situation.SituationResponse;

public interface SituationService {

    @Transactional
    SituationResponse getSituations(Long userId);
}
