package sopt.nottodo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sopt.nottodo.domain.Mission;
import sopt.nottodo.domain.User;
import sopt.nottodo.dto.mission.MissionCompletionStatusDto;

import java.util.Date;
import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    List<Mission> findByUserAndActionDate(User user, Date actionDate);
    List<Mission> findByUser(User user);

    @Query(value =
            "select new sopt.nottodo.dto.mission.MissionCompletionStatusDto(mission.actionDate, mission.completionStatus)" +
            " from Mission mission" +
            " where mission.user = :user" +
            " and mission.actionDate >= :startDate and mission.actionDate < :lastDate" +
            " order by mission.actionDate")
    List<MissionCompletionStatusDto> findByUserAndActionDateRange(
            @Param("user") User user,
            @Param("startDate") Date startDate,
            @Param("lastDate") Date lastDate
    );
}
