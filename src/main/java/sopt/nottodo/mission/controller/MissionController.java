package sopt.nottodo.mission.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.nottodo.common.util.response.ResponseCode;
import sopt.nottodo.common.util.response.ResponseDataMessage;
import sopt.nottodo.common.util.response.ResponseMessage;
import sopt.nottodo.common.util.response.ResponseNonDataMessage;
import sopt.nottodo.mission.service.MissionService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mission")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/daily/{date}")
    public ResponseEntity<ResponseMessage> getDailyMission(@PathVariable String date, HttpServletRequest request) {
        Long userId = Long.valueOf(request.getUserPrincipal().getName());
        return ResponseDataMessage.toResponseEntity(
                ResponseCode.GET_DAILY_MISSIONS_SUCCESS,
                missionService.getDailyMission(date, userId)
        );
    }

    @GetMapping("/week/{startDate}")
    public ResponseEntity<ResponseMessage> getWeeklyMission(@PathVariable String startDate, HttpServletRequest request) {
        Long userId = Long.valueOf(request.getUserPrincipal().getName());
        return ResponseDataMessage.toResponseEntity(
                ResponseCode.GET_WEEKLY_MISSIONS_SUCCESS,
                missionService.getWeeklyMission(startDate, userId)
        );
    }

    @DeleteMapping("/{missionId}")
    public ResponseEntity<ResponseMessage> deleteMission(@PathVariable Long missionId, HttpServletRequest request) {
        Long userId = Long.valueOf(request.getUserPrincipal().getName());
        missionService.deleteMission(missionId, userId);
        return ResponseNonDataMessage.toResponseEntity(
                ResponseCode.DELETE_MISSON_SUCCESS
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
