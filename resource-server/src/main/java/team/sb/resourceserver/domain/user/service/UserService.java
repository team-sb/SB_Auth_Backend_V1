package team.sb.resourceserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import team.sb.resourceserver.domain.user.api.dto.UserInfoResponse;
import team.sb.resourceserver.domain.user.entity.User;
import team.sb.resourceserver.domain.user.repository.UserRepository;
import team.sb.resourceserver.global.exception.AuthenticationNotFoundException;
import team.sb.resourceserver.global.exception.UserNotFoundException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserInfoResponse getUserInfo() {
        return new UserInfoResponse(getCurrentUser());
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(principal);

//        if(!(principal instanceof UserDetails))
//            throw AuthenticationNotFoundException.EXCEPTION;

        return userRepository.findByEmail(principal.toString())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}
