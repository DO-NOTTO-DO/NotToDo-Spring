package sopt.nottodo.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.nottodo.recommend.domain.RecommendSituation;

@Repository
public interface RecommendRepository extends JpaRepository<RecommendSituation, Long> {
}
