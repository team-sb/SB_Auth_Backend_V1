package team.sb.authorizationserver.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import team.sb.authorizationserver.domain.image.entity.Image;
import team.sb.authorizationserver.domain.image.service.ImageService;
import team.sb.authorizationserver.domain.user.api.dto.SignupRequest;
import team.sb.authorizationserver.domain.user.entity.User;
import team.sb.authorizationserver.domain.user.repository.UserRepository;
import team.sb.authorizationserver.global.exception.UserAlreadyExistsException;

@RequiredArgsConstructor
@Service
public class UserService {

    private final ImageService imageService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(SignupRequest request, MultipartFile profile) {
        if(userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw UserAlreadyExistsException.EXCEPTION;
        }

        userRepository.save(User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .gender(request.getGender())
                .birthDay(request.getBirthDay())
                .profile(checkImage(profile))
                .build());
    }

    private Image checkImage(MultipartFile profile) {
        if(profile.isEmpty()) {
            return null;
        }

        return imageService.addImage(profile);
    }

}
