package team.sb.authorizationserver.domain.user.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team.sb.authorizationserver.domain.user.api.dto.SignupRequest;
import team.sb.authorizationserver.domain.user.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void signup(@RequestPart @Valid SignupRequest request, @RequestPart(required = false) MultipartFile profile) {
        userService.signup(request, profile);
    }

}
