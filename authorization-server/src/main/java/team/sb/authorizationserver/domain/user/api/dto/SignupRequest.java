package team.sb.authorizationserver.domain.user.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignupRequest {

    private String uid;
    private String password;
    private String name;

}
