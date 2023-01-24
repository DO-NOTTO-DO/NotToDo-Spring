package sopt.nottodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.nottodo.domain.RecommendMission;

import java.util.List;

@Repository
public interface RecommendMissionRepository extends JpaRepository<RecommendMission, Long> {
    List<RecommendMission> findByRecommendCategoryId(Long recommendCategoryId);
}
