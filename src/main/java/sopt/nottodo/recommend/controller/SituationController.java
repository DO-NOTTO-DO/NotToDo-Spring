package sopt.nottodo.recommend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.nottodo.recommend.service.SituationService;
import sopt.nottodo.common.util.response.ResponseCode;
import sopt.nottodo.common.util.response.ResponseDataMessage;
import sopt.nottodo.common.util.response.ResponseMessage;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/situation")
public class SituationController {

    private final SituationService situationService;

    @GetMapping()
    public ResponseEntity<ResponseMessage> getSituations(HttpServletRequest request) {
        Long userId = Long.valueOf(request.getUserPrincipal().getName());
        return ResponseDataMessage.toResponseEntity(
                ResponseCode.GET_SITUATIONS_SUCCESS,
                situationService.getSituations(userId)
        );
    }
}
