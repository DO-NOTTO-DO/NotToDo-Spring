package sopt.nottodo.mission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sopt.nottodo.auth.domain.User;
import sopt.nottodo.mission.domain.Mission;

import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

    List<Mission> findByUserOrderByCreatedAtDesc(User user);

    //select daily_mission.id, mission.id, mission.title, daily_mission.date, completion_status, situation_id
    //from mission, daily_mission
    //where mission.user_id = 1 and mission.id = daily_mission.mission_id
    //and daily_mission.date between '2023-02-20' and '2023-02-21';

//    @Query(value =
//            "select DailyMission " +
//            "from Mission mission, DailyMission dailyMission " +
//                    "where mission.user = :user " +
//                    "and mission.id = dailyMission")
//    List<Mission> findByUserAndActionDate(User user, Date actionDate);
//    List<Mission> findByUser(User user);
//    List<Mission> findByUserOrderByCreatedAtDesc(User user);

//    @Query(value =
//            "select new sopt.nottodo.dto.mission.MissionCompletionStatusDto(mission.actionDate, mission.completionStatus)" +
//            " from Mission mission" +
//            " where mission.user = :user" +
//            " and mission.actionDate >= :startDate and mission.actionDate < :lastDate" +
//            " order by mission.actionDate")
//    List<MissionCompletionStatusDto> findByUserAndActionDateRange(
//            @Param("user") User user,
//            @Param("startDate") Date startDate,
//            @Param("lastDate") Date lastDate
//    );
}
