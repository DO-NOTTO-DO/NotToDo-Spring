package sopt.nottodo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.nottodo.service.MissionService;
import sopt.nottodo.util.response.ResponseCode;
import sopt.nottodo.util.response.ResponseDataMessage;
import sopt.nottodo.util.response.ResponseMessage;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/daily/{date}")
    public ResponseEntity<ResponseMessage> getDailyMissions(@PathVariable String date, HttpServletRequest request) {
        Long userId = Long.valueOf(request.getUserPrincipal().getName());
        return ResponseDataMessage.toResponseEntity(
                ResponseCode.GET_DAILY_MISSIONS_SUCCESS,
                missionService.getDailyMission(date, userId)
        );
    }

    @GetMapping("/week/{startDate}")
    public ResponseEntity<ResponseMessage> getWeeklyMissionPercentage(
            @PathVariable String startDate, HttpServletRequest request) {
        Long userId = Long.valueOf(request.getUserPrincipal().getName());
        return ResponseDataMessage.toResponseEntity(
                ResponseCode.GET_WEEKLY_MISSIONS_PERCENTAGE_SUCCESS,
                missionService.getWeeklyMissionPercentage(startDate, userId)
        );
    }

    @GetMapping("/recent")
    public ResponseEntity<ResponseMessage> getRecentMission(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getUserPrincipal().getName());
        return ResponseDataMessage.toResponseEntity(
                ResponseCode.GET_RECENT_MISSIONS_SUCCESS,
                missionService.getRecentMissions(userId)
        );
    }
}
