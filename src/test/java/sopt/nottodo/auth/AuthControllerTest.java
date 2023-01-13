package sopt.nottodo.auth;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import sopt.nottodo.common.ControllerTest;
import sopt.nottodo.dto.auth.LoginRequest;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthControllerTest extends ControllerTest {

    @Test
    @DisplayName("로그인 시 request body에 빈 값이 들어가면 예외가 발생한다.")
    void emptyNickNameInEditing() throws Exception {
        // given
        LoginRequest request = new LoginRequest("TOKEN", "FCMTOKEN", "null", "test0@example.com");

        // when, then
        assertBadRequestFromPost("/api/auth/login/TEST", request, "");
    }

    private void assertBadRequestFromPost(String uri, Object request, String errorMessage) throws Exception {
        mockMvc.perform(post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                ).andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString(errorMessage)));
    }
}
