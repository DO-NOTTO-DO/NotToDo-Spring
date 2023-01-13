package sopt.nottodo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sopt.nottodo.dto.auth.LoginRequest;
import sopt.nottodo.service.MissionService;
import sopt.nottodo.util.response.ResponseCode;
import sopt.nottodo.util.response.ResponseDataMessage;
import sopt.nottodo.util.response.ResponseMessage;

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
}
