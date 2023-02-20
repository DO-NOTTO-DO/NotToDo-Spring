package sopt.nottodo.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sopt.nottodo.auth.domain.User;
import sopt.nottodo.auth.repository.UserRepository;
import sopt.nottodo.common.util.exception.CustomException;
import sopt.nottodo.common.util.response.ResponseCode;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new CustomException(ResponseCode.USER_NOT_FOUND));
    }
}
