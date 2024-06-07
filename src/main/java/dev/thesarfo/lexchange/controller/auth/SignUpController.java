package dev.thesarfo.lexchange.controller.auth;

import dev.thesarfo.lexchange.model.dto.request.user.UserRegisterRequest;
import dev.thesarfo.lexchange.model.success.SuccessMessages;
import dev.thesarfo.lexchange.service.auth.register.UserRegisterServiceImpl;
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
@RequestMapping("api/v1/auth")
public class SignUpController{

    private final UserRegisterServiceImpl userRegisterService;

    @PostMapping("/signup")
    public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterRequest request){
        return ResponseHandler.successResponse(SuccessMessages.REGISTRATION_SUCCESSFUL,
                userRegisterService.registerUser(request), HttpStatus.CREATED);
    }
}
