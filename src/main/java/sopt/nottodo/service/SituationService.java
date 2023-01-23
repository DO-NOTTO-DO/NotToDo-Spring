package sopt.nottodo.service;

import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.dto.situation.SituationResponse;

public interface SituationService {

    @Transactional
    SituationResponse getSituations(Long userId);
}
