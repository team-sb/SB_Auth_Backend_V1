package team.sb.resourceserver.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    AUTHENTICATION_NOT_FOUND(404, "Authentication Not Found"),

    USER_NOT_FOUND(404, "User Not Found");

    private final int status;
    private final String message;

}
