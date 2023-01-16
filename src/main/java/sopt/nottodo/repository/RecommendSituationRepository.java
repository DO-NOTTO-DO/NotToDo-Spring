package sopt.nottodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.nottodo.domain.RecommendSituation;

import java.util.List;

@Repository
public interface RecommendSituationRepository extends JpaRepository<RecommendSituation, Long> {

    List<RecommendSituation> findAll();
}
