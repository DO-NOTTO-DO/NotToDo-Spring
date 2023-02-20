package sopt.nottodo.recommend.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sopt.nottodo.recommend.service.EnvironmentService;
import sopt.nottodo.common.util.response.ResponseCode;
import sopt.nottodo.common.util.response.ResponseDataMessage;
import sopt.nottodo.common.util.response.ResponseMessage;

@RestController
@RequiredArgsConstructor
@RequestMapping("/environment")
public class EnvironmentController {

    private final EnvironmentService environmentService;

//    @GetMapping("/category")
//    public ResponseEntity<ResponseMessage> getCategory() {
//        return ResponseDataMessage.toResponseEntity(
//                ResponseCode.GET_RECOMMEND_CATEGORY_SUCCESS,
//                environmentService.getCategory()
//        );
//    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ResponseMessage> getMissionByCategory(@PathVariable Long categoryId) {
        return ResponseDataMessage.toResponseEntity(
                ResponseCode.GET_RECOMMEND_CATEGORY_SUCCESS,
                environmentService.getMissionByCategory(categoryId)
        );
    }
}
