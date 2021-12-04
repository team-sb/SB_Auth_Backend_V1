package team.sb.resourceserver.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.sb.resourceserver.domain.user.api.dto.UserInfoResponse;
import team.sb.resourceserver.domain.user.entity.User;
import team.sb.resourceserver.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserController {

    private final UserRepository userRepository;

    @GetMapping("/user")
    public UserInfoResponse getUserInfo() {
        User user = getCurrentUser();

        return new UserInfoResponse(user);
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByEmail(principal.toString())
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

}
