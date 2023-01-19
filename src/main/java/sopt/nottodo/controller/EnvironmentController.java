package sopt.nottodo.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.nottodo.service.EnvironmentService;
import sopt.nottodo.util.response.ResponseCode;
import sopt.nottodo.util.response.ResponseDataMessage;
import sopt.nottodo.util.response.ResponseMessage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/environment")
public class EnvironmentController {
    private final EnvironmentService environmentService;

    @GetMapping("/category")
    public ResponseEntity<ResponseMessage> getCategory() {
        System.out.println(environmentService.getCategory());
        return ResponseDataMessage.toResponseEntity(
                ResponseCode.GET_RECOMMEND_CATEGORY_SUCCESS,
                environmentService.getCategory()
        );
    }
}
