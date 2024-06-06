package dev.thesarfo.lexchange.service.auth.register;

import dev.thesarfo.lexchange.model.dto.request.user.UserRegisterRequest;
import dev.thesarfo.lexchange.model.dto.response.user.UserResponseDto;

public interface UserRegisterService{
    UserResponseDto registerUser(UserRegisterRequest userRegisterRequest);
}
