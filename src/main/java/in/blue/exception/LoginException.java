package in.blue.exception;

import in.blue.model.LoginDetails;

public class LoginException extends RuntimeException {

    private LoginDetails loginDetails;

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public LoginException(String message, LoginDetails loginDetails) {
        super(message);
        this.loginDetails = loginDetails;
    }

    public LoginException(String message, Throwable throwable, LoginDetails loginDetails) {
        super(message, throwable);
        this.loginDetails = loginDetails;
    }

    public LoginDetails getLoginDetails() {
        return loginDetails;
    }

}
