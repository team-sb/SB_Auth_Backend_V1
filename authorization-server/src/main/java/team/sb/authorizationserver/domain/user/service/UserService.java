package team.sb.authorizationserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.sb.authorizationserver.domain.user.api.dto.SignupRequest;
import team.sb.authorizationserver.domain.user.entity.User;
import team.sb.authorizationserver.domain.user.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest request) {
        if(userRepository.findByUid(request.getUid()).isPresent()) {
            throw new IllegalArgumentException("user already exists");
        }

        userRepository.save(User.builder()
                .uid(request.getUid())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .provider(request.getProvider())
                .build());
    }

}
