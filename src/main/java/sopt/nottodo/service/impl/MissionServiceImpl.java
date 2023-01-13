package sopt.nottodo.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.domain.Mission;
import sopt.nottodo.domain.User;
import sopt.nottodo.dto.mission.DailyMissionResponse;
import sopt.nottodo.repository.MissionRepository;
import sopt.nottodo.repository.UserRepository;
import sopt.nottodo.service.MissionService;
import sopt.nottodo.util.exception.CustomException;
import sopt.nottodo.util.response.ResponseCode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionServiceImpl implements MissionService {

    private final MissionRepository missionRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public DailyMissionResponse getDailyMission(String today, Long userId) {
        User user = findUser(userId);
        List<Mission> missions = missionRepository.findByUserAndActionDate(user, getToday(today));
        return new DailyMissionResponse(missions);
    }

    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new CustomException(ResponseCode.USER_NOT_FOUND)
        );
    }

    private Date getToday(String today) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(today);
        } catch (ParseException e) {
            throw new CustomException(ResponseCode.INVALID_DATE_FORMAT);
        }
    }
}
