package dev.thesarfo.lexchange.service.auth.login;

import dev.thesarfo.lexchange.model.dto.request.user.UserLoginRequest;
import dev.thesarfo.lexchange.model.dto.response.user.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface UserLoginService {
    JwtResponse signin(UserLoginRequest userLoginRequest);
}
