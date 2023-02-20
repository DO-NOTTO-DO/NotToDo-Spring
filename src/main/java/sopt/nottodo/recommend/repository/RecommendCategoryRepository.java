package sopt.nottodo.recommend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.nottodo.domain.RecommendCategory;

@Repository
public interface RecommendCategoryRepository extends JpaRepository<RecommendCategory, Long> {

}
