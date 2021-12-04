package team.sb.authorizationserver.domain.user.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.sb.authorizationserver.domain.user.entity.Gender;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SignupRequest {

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private Gender gender;
    private LocalDateTime birthDay;

}
