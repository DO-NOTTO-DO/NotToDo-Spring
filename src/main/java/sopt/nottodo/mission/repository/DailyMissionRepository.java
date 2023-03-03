package sopt.nottodo.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.nottodo.auth.domain.User;
import sopt.nottodo.mission.domain.DailyMission;

import java.util.Date;
import java.util.List;

@Repository
public interface DailyMissionRepository extends JpaRepository<DailyMission, Long> {

    List<DailyMission> findByDate(Date date);
    List<DailyMission> findByDateBetween(Date startDate, Date lastDate);

    List<DailyMission> findByOrderByCreatedAtDesc();
    List<DailyMission> findByMission_UserIdAndDateBetween(Long userId, Date startDate, Date lastDate);
}
