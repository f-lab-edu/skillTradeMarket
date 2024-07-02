package com.flab.skilltrademarket.global.security.encryption;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("SHA-256 validate encryption")
class SHA256EncoderTest {

    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        passwordEncoder = new SHA256Encoder();
    }

    @DisplayName("encryption success")
    @Test
    void successEncode() {
        // given
        String rawPassword = "password";

        // when
        String result = passwordEncoder.encode(rawPassword);

        // then
        String expectValue = "5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8";
        assertThat(result).isEqualTo(expectValue);
    }

    @DisplayName("password is null then fail")
    @Test
    void failEncode_Null_Password() {
        // when
        // then
        assertThatThrownBy(() -> passwordEncoder.encode(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}