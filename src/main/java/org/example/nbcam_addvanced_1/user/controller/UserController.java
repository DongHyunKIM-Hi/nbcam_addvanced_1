package org.example.nbcam_addvanced_1.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nbcam_addvanced_1.common.utils.JwtUtil;
import org.example.nbcam_addvanced_1.user.model.response.LoginResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final JwtUtil jwtUtil;

    @GetMapping("/get")
    public String getUserInfo(HttpServletRequest request) {
        log.info("호출 : " + request.getAttribute("username"));
        return request.getAttribute("username") + "이 호출 되었습니다..";
    }

    // 토큰 생성 테스트
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login() {

        String token  = jwtUtil.generateToken("kim-dong-hyun");

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    // 토큰 검증 테스트
    @GetMapping("/validate")
    public ResponseEntity<Boolean> checkValidate(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        String jwt = authorizationHeader.substring(7);

        Boolean validate = jwtUtil.validateToken(jwt);

        return ResponseEntity.ok(validate);
    }

    // 토큰 복호화 테스트
    @GetMapping("/username")
    public ResponseEntity<String> getUsername(HttpServletRequest request) {

        String authorizationHeader = request.getHeader("Authorization");

        String jwt = authorizationHeader.substring(7);

        String username = jwtUtil.extractUsername(jwt);

        return ResponseEntity.ok(username);
    }

}
