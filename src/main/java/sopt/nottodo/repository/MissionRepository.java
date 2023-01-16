package sopt.nottodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.nottodo.domain.Mission;
import sopt.nottodo.domain.User;

import java.util.Date;
import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    List<Mission> findByUserAndActionDate(User user, Date actionDate);
    List<Mission> findByUser(User user);
}
