package com.the.soundspace.user.model.entities;

import com.the.soundspace.common.enums.UserType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class UserEntityTest {

/*

    @Test
    @DisplayName("이메일 형식이 올바르지 않을 때 유효성 검증이 실패해야 한다.")
    void whenEmailIsInvalid_thenValidationFails() {
        // given
        UserEntity user = UserEntity.builder()
                .email("invalid-email-format") // Invalid email format
                .nick("유저")
                .userType(UserType.REGULAR_MEMBER).build();

        assertThat(user).isNull();

    }
*/

}