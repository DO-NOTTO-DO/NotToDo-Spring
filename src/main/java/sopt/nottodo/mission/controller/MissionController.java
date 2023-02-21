package sopt.nottodo.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.nottodo.common.util.response.ResponseCode;
import sopt.nottodo.common.util.response.ResponseDataMessage;
import sopt.nottodo.common.util.response.ResponseMessage;
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
}
