package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.domain.user.dto.UserDto;
import com.flab.skilltrademarket.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    /**
     * 이메일 중복확인
     *
     * @param email : 이메일
     * @return 200
     */
    @PostMapping("/user-email/{email}/exist")
    public ResponseEntity<Boolean> checkDuplicateEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.checkDuplicateEmail(email));
    }

    /**
     * 닉네임 중복확인
     *
     * @param nickname : 닉네임
     * @return 200
     */
    @PostMapping("/user-nickName/{nickName}/exist")
    public ResponseEntity<Boolean> checkDuplicateNickName(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.checkDuplicateNickName(nickname));
    }

    /**
     * 회원가입
     *
     * @param userDto : 회원가입할 entity
     */
    @PostMapping("/user/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Valid @RequestBody UserDto userDto) {
        userService.save(userDto);
    }


}
