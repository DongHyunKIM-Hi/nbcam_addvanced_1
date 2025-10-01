package org.example.nbcam_addvanced_1.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nbcam_addvanced_1.user.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/normal")
@RequiredArgsConstructor
@Slf4j
public class NormalController {

    @GetMapping("/get")
    public String getUserInfo(@AuthenticationPrincipal User user) {
        log.info(user.getUsername());
        return "일반 페이지 리소스가 허가 되었습니다.";
    }
}
