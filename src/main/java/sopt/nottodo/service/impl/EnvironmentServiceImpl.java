package sopt.nottodo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.domain.RecommendCategory;
import sopt.nottodo.dto.environment.CategoryDto;
import sopt.nottodo.dto.mission.MissionDto;
import sopt.nottodo.repository.EnvironmentRepository;
import sopt.nottodo.service.EnvironmentService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


@Service
@RequiredArgsConstructor
public class EnvironmentServiceImpl implements EnvironmentService {
    private final EnvironmentRepository environmentRepository;
    @Override
    public List<CategoryDto> getCategory() {
        List<RecommendCategory> categories = environmentRepository.findAll();
        return categories.stream()
                .map(category-> new CategoryDto(category.getId(), category.getName(), category.getImage(), category.getActiveImage()))
                .collect(Collectors.toUnmodifiableList());
    }
}
