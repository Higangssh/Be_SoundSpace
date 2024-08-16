package com.the.soundspace.user.service;

import com.the.soundspace.common.enums.UserType;
import com.the.soundspace.token.domain.JwtToken;
import com.the.soundspace.user.infrastructure.repository.UserRepository;
import com.the.soundspace.user.model.entities.UserEntity;
import com.the.soundspace.util.NicknameGenerator;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtToken jwtToken;

    /**
     *
     * @param user
     * @param response
     * @return 가입된 유저의 경우 token 발급 가입 안된 사람의 경우 가입후 token 발급
     */
    @Override
    public Long checkAndJoinUser(UserEntity user, HttpServletResponse response) {

        try {

            if (userRepository.existsByNick(user.getNick())) {
                log.error("User {} already exists", user.getNick());
                return null;
            }

            UserEntity returnUser = userRepository.findByEmail(user.getEmail()).orElseGet(
                    () -> userRepository.save(user));
//            String accessJwt = jwtToken.createAccessJwt(returnUser.getId());
//            CookieManager.addJwtCookieAndHeader(response, accessJwt);
            return returnUser.getId();
        } catch (IllegalArgumentException exception) {
            log.error("Invalid argument during user check or join: {}", exception.getMessage());
            return null;
        }

    }

    @Override
    public UserEntity createUser(String email) {
        String nickname = NicknameGenerator.generate();
        return UserEntity.builder()
                .email(email)
                .nick(nickname)
                .userType(UserType.REGULAR_MEMBER)
                .build();
    }
}
