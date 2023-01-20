package sopt.nottodo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.domain.Mission;
import sopt.nottodo.domain.User;
import sopt.nottodo.dto.mission.*;
import sopt.nottodo.repository.MissionRepository;
import sopt.nottodo.repository.UserRepository;
import sopt.nottodo.service.MissionService;
import sopt.nottodo.util.exception.CustomException;
import sopt.nottodo.util.response.ResponseCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private static final Integer MONDAY = 1;
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public List<MissionDto> getDailyMission(String today, Long userId) {
        User user = findUser(userId);
        List<Mission> missions = missionRepository.findByUserAndActionDate(user, getToday(today));
        return missions.stream()
                .map(MissionDto::new)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<DailyMissionPercentageDto> getWeeklyMissionPercentage(String startDate, Long userId) {
        User user = findUser(userId);
        Date startDay = getToday(startDate);
        validateMonday(startDay);
        Date finishDay = getWeekAfter(startDay);
        List<MissionCompletionStatusDto> missions
                = missionRepository.findByUserAndActionDateRange(user, startDay, finishDay);
        return calculateWeeklyMissionPercentage(missions);
    }

    @Override
    public List<MissionTitleDto> getRecentMissions(Long userId) {
        User user = findUser(userId);
        List<MissionTitleDto> recentMissions = missionRepository.findByUserOrderByCreatedAtDesc(user).stream()
                .map(MissionTitleDto::new)
                .distinct()
                .collect(Collectors.toUnmodifiableList());
        return recentMissions;
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ResponseCode.USER_NOT_FOUND)
        );
    }

    private List<DailyMissionPercentageDto> calculateWeeklyMissionPercentage(List<MissionCompletionStatusDto> missions) {
        Map<Date, DailyMissionPercentageCalculateDto> dailyMissions = makeDailyMissionPercentageCalculateDto(missions);
        addMissionPoints(missions, dailyMissions);
        return dailyMissions.values().stream()
                .map(DailyMissionPercentageCalculateDto::convertToResponse)
                .sorted(Comparator.comparing(DailyMissionPercentageDto::getActionDate))
                .collect(Collectors.toUnmodifiableList());
    }

    private Map<Date, DailyMissionPercentageCalculateDto> makeDailyMissionPercentageCalculateDto(List<MissionCompletionStatusDto> missions) {
        Map<Date, DailyMissionPercentageCalculateDto> dailyMissions = new LinkedHashMap<>();
        missions.stream()
                .collect(Collectors.groupingByConcurrent(MissionCompletionStatusDto::getActionDate))
                .forEach((key, value) -> {
                    dailyMissions.put(key, new DailyMissionPercentageCalculateDto(key, value.size()));
                });
        return dailyMissions;
    }

    private void addMissionPoints(List<MissionCompletionStatusDto> missions, Map<Date, DailyMissionPercentageCalculateDto> dailyMissions) {
        missions.forEach(mission -> {
            float point = mission.getCompletionStatus().getPoint();
            dailyMissions.get(mission.getActionDate()).addPoint(point);
        });
    }

    private Date getToday(String today) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT);
            return format.parse(today);
        } catch (ParseException e) {
            throw new CustomException(ResponseCode.INVALID_DATE_FORMAT);
        }
    }

    private void validateMonday(Date day) {
        LocalDate localDate = dateToLocalDate(day);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        int dayOfWeekNumber = dayOfWeek.getValue();
        if (dayOfWeekNumber != MONDAY) {
            throw new CustomException(ResponseCode.NOT_MONDAY);
        }
    }

    private LocalDate dateToLocalDate(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    private Date getWeekAfter(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        Calendar calendar = Calendar.getInstance();
        calendar.set(localDate.getYear(), localDate.getMonthValue() - 1, localDate.getDayOfMonth(), 0, 0, 0);
        calendar.add(Calendar.DATE, 7);
        return calendar.getTime();
    }
}
