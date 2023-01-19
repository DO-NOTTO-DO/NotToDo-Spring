package sopt.nottodo.service;

import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.domain.RecommendCategory;
import sopt.nottodo.dto.environment.CategoryDto;

import java.util.List;

public interface EnvironmentService {

    List<CategoryDto> getCategory();
}
