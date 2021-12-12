package team.sb.authorizationserver.global.exception;

import team.sb.authorizationserver.global.error.ErrorCode;
import team.sb.authorizationserver.global.error.exception.SBException;

public class UserAlreadyExistsException extends SBException {

    public static SBException EXCEPTION =
            new UserAlreadyExistsException();

    private UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }

}
