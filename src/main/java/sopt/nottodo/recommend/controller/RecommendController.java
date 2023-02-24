package sopt.nottodo.recommend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.nottodo.common.util.response.ResponseCode;
import sopt.nottodo.common.util.response.ResponseDataMessage;
import sopt.nottodo.common.util.response.ResponseMessage;
import sopt.nottodo.recommend.service.RecommendService;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class RecommendController {

    private final RecommendService recommendService;

    @GetMapping("/situation")
    public ResponseEntity<ResponseMessage> getRecommendSituation() {
        return ResponseDataMessage.toResponseEntity(
                ResponseCode.GET_RECOMMEND_SITUATIONS_SUCCESS,
                recommendService.getRecommendSituation()
        );
    }
}
