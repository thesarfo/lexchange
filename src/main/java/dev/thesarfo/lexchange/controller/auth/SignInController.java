package dev.thesarfo.lexchange.controller.auth;

import dev.thesarfo.lexchange.model.dto.request.user.UserLoginRequest;
import dev.thesarfo.lexchange.model.success.SuccessMessages;
import dev.thesarfo.lexchange.service.auth.login.UserLoginServiceImpl;
import dev.thesarfo.lexchange.util.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth/signin")
public class SignInController {

private final UserLoginServiceImpl userLoginService;

    @PostMapping
    public ResponseEntity<Object> signin(@Valid @RequestBody UserLoginRequest request){
        return ResponseHandler.successResponse(SuccessMessages.LOGIN_SUCCESSFUL,
                userLoginService.signin(request), HttpStatus.OK);
    }
}
