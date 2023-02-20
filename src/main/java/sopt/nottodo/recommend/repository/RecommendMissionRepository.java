package sopt.nottodo.recommend.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sopt.nottodo.recommend.domain.RecommendMission;

import java.util.List;

@Repository
public interface RecommendMissionRepository extends JpaRepository<RecommendMission, Long> {

    @Query("select r from RecommendMission r join fetch r.recommendActions")
    @EntityGraph(attributePaths = {"title"})
    List<RecommendMission> findByRecommendCategoryId(Long recommendCategoryId);
}
