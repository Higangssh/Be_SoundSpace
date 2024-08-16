package com.the.soundspace.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.the.soundspace.config.properties.JwtPropertiesConfig;
import com.the.soundspace.token.domain.JwtToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class JwtTokenTest {

    private JwtPropertiesConfig mockConfig;
    private JwtToken jwtToken;

    @BeforeEach
    void setUp() {
        // JwtPropertiesConfig를 Mockito로 모킹
        mockConfig = Mockito.mock(JwtPropertiesConfig.class);

        // 액세스 토큰 프로퍼티를 모킹
        JwtPropertiesConfig.TokenProperty mockTokenProperty = Mockito.mock(JwtPropertiesConfig.TokenProperty.class);
        when(mockTokenProperty.getSecret()).thenReturn("test-secretgfhfghffhfghgfhfghgfhfghfghf");
        when(mockTokenProperty.getExpiration()).thenReturn(3600);

        // 모킹한 액세스 토큰 프로퍼티를 JwtPropertiesConfig에 설정
        when(mockConfig.getAccessToken()).thenReturn(mockTokenProperty);

        // JwtToken 인스턴스 생성
        jwtToken = new JwtToken(mockConfig);
    }

    @Test
    @DisplayName("토큰이 잘 생성되는지 테스트")
    void testCreateJwt() {
        //given
        Long memberId = 123L;

        //when
        String token = jwtToken.createAccessJwt(memberId);

        //then
        assertNotNull(token);
        System.out.println("Generated Token: " + token);
    }


    @Test
    @DisplayName("토큰 디코딩 성공 검사")
    void testDecodedToken_ValidToken() {
        // Given
        Long memberId = 123L;
        String token= jwtToken.createAccessJwt(memberId);

        // When
        Long decodedMemberId = jwtToken.decodedAccessToken(token);
        System.out.println("memberId: " + memberId);
        System.out.println("decodedMemberId: " + decodedMemberId);

        //then
        assertThat(memberId).isEqualTo(decodedMemberId);
    }

    @Test
    @DisplayName("잘못된 토큰으로 디코딩 실패 테스트")
    void testDecodedToken_InvalidToken() {
        // Given: An invalid JWT token
        String invalidToken = "invalid-token";

        // When: We attempt to decode the invalid token
        Long decodedMemberId = jwtToken.decodedAccessToken(invalidToken);

        // Then: Decoding should fail and return null
        assertNull(decodedMemberId);
    }

    @Test
    @DisplayName("잘못된 시크릿 값으로 생성된 토큰 디코딩 실패 테스트")
    void testDecodedToken_WrongSecret() {
        // Given: A token created with a different secret
        Long memberId = 123L;
        Algorithm algorithm = Algorithm.HMAC256("wrong-secret");
        String tokenWithWrongSecret = JWT.create()
                .withIssuer("SoundSpace")
                .withClaim("sub", memberId)
                .sign(algorithm);

        // When: We attempt to decode the token with the correct secret
        Long decodedMemberId = jwtToken.decodedAccessToken(tokenWithWrongSecret);

        // Then: Decoding should fail and return null
        assertNull(decodedMemberId);
    }

    @Test
    @DisplayName("만료된 토큰을 확인하는 테스트")
    void testIsTokenExpired() {
        // Given: A token with a very short expiration time
        Long memberId = 123L;
        Algorithm algorithm = Algorithm.HMAC256(mockConfig.getAccessToken().getSecret());
        String expiredToken = JWT.create()
                .withIssuer("SoundSpace")
                .withClaim("sub", memberId)
                .withExpiresAt(new Date(System.currentTimeMillis() - 1000))  // 이미 만료된 시간 설정
                .sign(algorithm);

        // When: We check if the token is expired
        boolean isExpired = jwtToken.isTokenExpired(expiredToken);

        // Then: The result should be true
        assertTrue(isExpired);
    }
}