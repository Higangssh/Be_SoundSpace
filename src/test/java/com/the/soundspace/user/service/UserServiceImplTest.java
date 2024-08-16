package com.the.soundspace.user.service;

import com.the.soundspace.common.enums.UserType;
import com.the.soundspace.user.infrastructure.repository.UserRepository;
import com.the.soundspace.user.model.entities.UserEntity;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
@ActiveProfiles(value = {"test","jwt","cors","oauth"})
public class UserServiceImplTest {
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("새로운 사용자를 추가하면 데이터베이스에 사용자가 저장되어야 한다.")
    void createUser() {
        // given
        UserEntity user = UserEntity.builder()
                .email("test@example.com")
                .nick("새로운 유저")
                .userType(UserType.REGULAR_MEMBER)
                .profile("null")
                .nick("하이하이")
                .build();

        // when

        Long userId = userService.checkAndJoinUser(user, null);

        // then
        assertThat(userId).isNotNull();
        UserEntity persistedUser = userRepository.findById(userId).orElse(null);
        assertThat(persistedUser).isNotNull();
        assertThat(persistedUser.getEmail()).isEqualTo("test@example.com");
    }

    @Test
    @DisplayName("이미 존재하는 사용자가 있을 때 새로운 사용자가 추가되지 않아야 한다.")
    void whenUserExists_thenDoNotCreateNewUser() {
        // given
        UserEntity existingUser = UserEntity.builder()
                .email("existing@example.com")
                .nick("기존 유저")
                .userType(UserType.REGULAR_MEMBER)
                .profile("existingProfile")
                .build();
        userRepository.save(existingUser);

        UserEntity newUser = UserEntity.builder()
                .email("existing@example.com")
                .nick("새로운 유저")
                .userType(UserType.REGULAR_MEMBER)
                .profile("newProfile")
                .build();

        // when
        Long userId = userService.checkAndJoinUser(newUser, null);

        // then
        assertThat(userId).isEqualTo(existingUser.getId());
        UserEntity persistedUser = userRepository.findById(userId).orElse(null);
        assertThat(persistedUser).isNotNull();
        assertThat(persistedUser.getEmail()).isEqualTo("existing@example.com");
        assertThat(persistedUser.getNick()).isEqualTo("기존 유저");
    }

    @Test
    @DisplayName("중복된 닉네임으로 사용자를 추가하려고 할 때 null을 반환해야 한다.")
    void whenNickNameExists_thenReturnNull() {
        // given
        UserEntity existingUser = UserEntity.builder()
                .email("existing@example.com")
                .nick("중복 닉네임")
                .userType(UserType.REGULAR_MEMBER)
                .profile("existingProfile")
                .build();
        userRepository.save(existingUser);

        UserEntity newUser = UserEntity.builder()
                .email("newuser@example.com")
                .nick("중복 닉네임")  // 동일한 닉네임 사용
                .userType(UserType.REGULAR_MEMBER)
                .profile("newProfile")
                .build();

        // when
        Long userId = userService.checkAndJoinUser(newUser, null);

        // then
        assertThat(userId).isNull();  // 중복 닉네임으로 인해 null이 반환되는지 확인
        UserEntity persistedUser = userRepository.findByEmail("newuser@example.com").orElse(null);
        assertThat(persistedUser).isNull();  // 새로운 사용자가 데이터베이스에 저장되지 않았음을 확인
    }



}