package team.sb.resourceserver.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sb.resourceserver.domain.user.api.dto.UserInfoResponse;
import team.sb.resourceserver.domain.user.service.UserService;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public UserInfoResponse getUserInfo() {
        return userService.getUserInfo();
    }

}
