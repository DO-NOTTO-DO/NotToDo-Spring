package sopt.nottodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.nottodo.domain.RecommendCategory;


import java.util.List;

@Repository
public interface EnvironmentRepository extends JpaRepository<RecommendCategory, Long> {
//    List<RecommendCategory> findAll();
}
