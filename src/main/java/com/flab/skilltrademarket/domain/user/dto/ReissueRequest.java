package com.flab.skilltrademarket.domain.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
@Schema(description = "토큰 재발급 요청")
public record ReissueRequest(
        @Schema(description = "재발급 토큰", example = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNkQG5hdmVyLmNvbSIsImlhdCI6MTcyNjcwNDUwNCwiZXhwIjoxNzI2NzA4MTA0LCJ1c2VybmFtZSI6ImFiY2RAbmF2ZXIuY29tIn0.yQcnxMy9zxmmAj-UdEfrj2Pe3Ixc7lfvsZjyI8S6pWRzeziZ2GAtpOsmKhdr0hlwSAmjtPJkrE5WLtOzRovfww")
        String refreshToken) {
}
