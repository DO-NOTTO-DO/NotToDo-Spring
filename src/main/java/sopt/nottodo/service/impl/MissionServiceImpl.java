package sopt.nottodo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sopt.nottodo.domain.CompletionStatus;
import sopt.nottodo.domain.Mission;
import sopt.nottodo.domain.User;
import sopt.nottodo.dto.mission.*;
import sopt.nottodo.repository.MissionRepository;
import sopt.nottodo.repository.UserRepository;
import sopt.nottodo.service.MissionService;
import sopt.nottodo.util.DateModule;
import sopt.nottodo.util.exception.CustomException;
import sopt.nottodo.util.response.ResponseCode;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    @Override
    public List<MissionDto> getDailyMission(String today, Long userId) {
        User user = findUser(userId);
        List<Mission> missions = missionRepository.findByUserAndActionDate(user, DateModule.getToday(today));
        return missions.stream()
                .map(MissionDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<DailyMissionPercentageDto> getWeeklyMissionPercentage(String startDate, Long userId) {
        User user = findUser(userId);
        Date startDay = DateModule.getToday(startDate);
        DateModule.validateMonday(startDay);
        Date finishDay = DateModule.getWeekAfter(startDay);
        List<MissionDateCompletionStatusDto> missions
                = missionRepository.findByUserAndActionDateRange(user, startDay, finishDay);
        return calculateWeeklyMissionPercentage(missions);
    }

    @Override
    public List<MissionTitleDto> getRecentMissions(Long userId) {
        User user = findUser(userId);
        return missionRepository.findByUserOrderByCreatedAtDesc(user).stream()
                .map(MissionTitleDto::new)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public MissionCompletionStatusDto changeMissionCompletionStatus(
            Long missionId, CompletionStatus completionStatus, Long userId) {
        Mission mission = findMissionById(missionId);
        validateUsersMission(mission, userId);
        mission.setCompletionStatus(completionStatus);
        missionRepository.save(mission);
        return new MissionCompletionStatusDto(mission);
    }

    @Override
    public void deleteMission(Long missionId, Long userId) {
        Mission mission = findMissionById(missionId);
        validateUsersMission(mission, userId);
        missionRepository.delete(mission);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ResponseCode.USER_NOT_FOUND)
        );
    }

    private List<DailyMissionPercentageDto> calculateWeeklyMissionPercentage(List<MissionDateCompletionStatusDto> missions) {
        Map<Date, DailyMissionPercentageCalculateDto> dailyMissions = makeDailyMissionPercentageCalculateDto(missions);
        addMissionPoints(missions, dailyMissions);
        return dailyMissions.values().stream()
                .map(DailyMissionPercentageCalculateDto::convertToResponse)
                .sorted(Comparator.comparing(DailyMissionPercentageDto::getActionDate))
                .collect(Collectors.toUnmodifiableList());
    }

    private Map<Date, DailyMissionPercentageCalculateDto> makeDailyMissionPercentageCalculateDto(List<MissionDateCompletionStatusDto> missions) {
        Map<Date, DailyMissionPercentageCalculateDto> dailyMissions = new LinkedHashMap<>();
        missions.stream()
                .collect(Collectors.groupingByConcurrent(MissionDateCompletionStatusDto::getActionDate))
                .forEach((key, value) -> {
                    dailyMissions.put(key, new DailyMissionPercentageCalculateDto(key, value.size()));
                });
        return dailyMissions;
    }

    private void addMissionPoints(List<MissionDateCompletionStatusDto> missions, Map<Date, DailyMissionPercentageCalculateDto> dailyMissions) {
        missions.forEach(mission -> {
            float point = mission.getCompletionStatus().getPoint();
            dailyMissions.get(mission.getActionDate()).addPoint(point);
        });
    }

    private Mission findMissionById(Long missionId) {
        return missionRepository.findById(missionId)
                .orElseThrow(() -> new CustomException(ResponseCode.MISSION_ID_NOT_FOUND));
    }

    private void validateUsersMission(Mission mission, Long userId) {
        User user = findUser(userId);
        User missionsUser = mission.getUser();
        if (!user.equals(missionsUser)) {
            throw new CustomException(ResponseCode.NOT_USERS_MISSION);
        }
    }
}
