package sopt.nottodo.mission.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.nottodo.auth.domain.User;
import sopt.nottodo.mission.domain.CompletionStatus;
import sopt.nottodo.mission.domain.DailyMission;
import sopt.nottodo.mission.dto.*;
import sopt.nottodo.mission.repository.DailyMissionRepository;
import sopt.nottodo.mission.repository.MissionRepository;
import sopt.nottodo.mission.service.MissionService;
import sopt.nottodo.auth.repository.UserRepository;
import sopt.nottodo.common.util.DateModule;
import sopt.nottodo.common.util.exception.CustomException;
import sopt.nottodo.common.util.response.ResponseCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private static final int DAY_COUNT_OF_WEEK = 7;

    private final MissionRepository missionRepository;
    private final DailyMissionRepository dailyMissionRepository;
    private final UserRepository userRepository;

    @Override
    public List<DailyMissionDTO> getDailyMission(String today, Long userId) {
        User user = findUser(userId);
        return dailyMissionRepository.findByDate(DateModule.getToday(today)).stream()
                .filter(dailyMission -> dailyMission.getMission().getUser().equals(user))
                .map(DailyMissionDTO::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<DailyMissionStatusDto> getWeeklyMission(String startDate, Long userId) {
        User user = findUser(userId);
        Date startDay = DateModule.getToday(startDate);
        DateModule.validateSunday(startDay);
        Date finishDay = DateModule.getWeekAfter(startDay);
        List<DailyMission> missions = dailyMissionRepository.findByDateBetween(startDay, finishDay).stream()
                .filter(dailyMission -> dailyMission.getMission().getUser().equals(user))
                .collect(Collectors.toUnmodifiableList());
        return makeWeeklyMissionStatusDto(missions, startDay);
    }
//
//    @Override
//    public List<MissionTitleDto> getRecentMissions(Long userId) {
//        User user = findUser(userId);
//        List<MissionTitleDto> recentMissions = missionRepository.findByUserOrderByCreatedAtDesc(user).stream()
//                .map(MissionTitleDto::new)
//                .distinct()
//                .collect(Collectors.toUnmodifiableList());
//        return recentMissions;
//    }
//
    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ResponseCode.USER_NOT_FOUND)
        );
    }

    private List<DailyMissionStatusDto> makeWeeklyMissionStatusDto(List<DailyMission> dailyMissions, final Date startDay) {
        List<List<DailyMission>> weeklyMissions = new ArrayList<>(DAY_COUNT_OF_WEEK);
        dailyMissions.forEach(dailyMission -> {
            int dayDiff = (int) getDaysDiff(dailyMission.getDate(), startDay);
            weeklyMissions.get(dayDiff).add(dailyMission);
        });

        List<DailyMissionStatusDto> result = new ArrayList<>();
        Date tempDate = startDay;
        for (List<DailyMission> missionsOfDay : weeklyMissions) {
            CompletionStatus completionStatus = getDailyCompletionStatus(missionsOfDay);
            String actionDate = tempDate.toString().substring(0, 10);
            tempDate = DateModule.getNextDay(startDay);
            result.add(new DailyMissionStatusDto(actionDate, completionStatus));
        }

        return result;
    }

    private long getDaysDiff(Date target, Date standard) {
        long secDiff = (target.getTime() - standard.getTime()) / 1000;
        return secDiff / (24 * 60 * 60);
    }

    private CompletionStatus getDailyCompletionStatus(List<DailyMission> dailyMissions) {
        long finishCount = dailyMissions.stream()
                .filter(dailyMission -> dailyMission.getCompletionStatus().equals(CompletionStatus.FINISH))
                .count();
        long notYetCount = dailyMissions.stream()
                .filter(dailyMission -> dailyMission.getCompletionStatus().equals(CompletionStatus.NOTYET))
                .count();
        if (notYetCount == 0 && finishCount != 0) {
            return CompletionStatus.FINISH;
        } else if (notYetCount != 0 && finishCount == 0) {
            return CompletionStatus.NOTYET;
        }
        return CompletionStatus.AMBIGUOUS;
    }
}
