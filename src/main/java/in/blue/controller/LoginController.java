package in.blue.controller;

import in.blue.exception.LoginException;
import in.blue.model.LoginDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @RequestMapping(value = "/do-login/{shouldThrow}", method = RequestMethod.GET)
    public String doLoginSuccess(@PathVariable boolean shouldThrow, ModelMap modelMap) {
        LoginDetails loginDetails = new LoginDetails();
        if(shouldThrow) {
            loginDetails.setLoginSuccessful(false);
            loginDetails.setLoginMessage("Not a valid token");
            loginDetails.setHttpStatus(HttpStatus.UNAUTHORIZED);
            throw new LoginException("Authentication failed", loginDetails);
        } else {
            loginDetails.setLoginSuccessful(true);
            loginDetails.setLoginMessage("Authorized");
            loginDetails.setHttpStatus(HttpStatus.OK);
            loginDetails.setEmailAddress("test@test.com");
            loginDetails.setUserName("test");
        }
        modelMap.addAttribute("loginDetails", loginDetails);
        return "login-check";
    }

    @ExceptionHandler(LoginException.class)
    public @ResponseBody ResponseEntity<LoginDetails> handleLoginException(final LoginException loginException){
        return new ResponseEntity<>(loginException.getLoginDetails(), loginException.getLoginDetails().getHttpStatus());
    }

}
