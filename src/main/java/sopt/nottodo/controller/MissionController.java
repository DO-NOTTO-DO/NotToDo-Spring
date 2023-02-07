package sopt.nottodo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.nottodo.domain.CompletionStatus;
import sopt.nottodo.dto.mission.CompletionStatusRequest;
import sopt.nottodo.service.MissionService;
import sopt.nottodo.util.response.ResponseCode;
import sopt.nottodo.util.response.ResponseDataMessage;
import sopt.nottodo.util.response.ResponseMessage;
import sopt.nottodo.util.response.ResponseNonDataMessage;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @PatchMapping("/{missionId}/check")
    public ResponseEntity<ResponseMessage> changeMissionCompletionStatus(
            @PathVariable Long missionId,
            @RequestBody CompletionStatusRequest completionStatusRequest,
            HttpServletRequest request) {
        Long userId = Long.valueOf(request.getUserPrincipal().getName());
        CompletionStatus completionStatus = CompletionStatus.from(completionStatusRequest.getCompletionStatus());
        return ResponseDataMessage.toResponseEntity(
                ResponseCode.CHANGE_MISSION_COMPLETION_STATUS_SUCCESS,
                missionService.changeMissionCompletionStatus(missionId, completionStatus, userId)
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
