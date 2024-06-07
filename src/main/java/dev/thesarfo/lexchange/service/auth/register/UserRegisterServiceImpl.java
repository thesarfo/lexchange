package dev.thesarfo.lexchange.service.auth.register;

import dev.thesarfo.lexchange.entity.user.User;
import dev.thesarfo.lexchange.exception.user.UserAlreadyExistsException;
import dev.thesarfo.lexchange.model.dto.request.user.UserRegisterRequest;
import dev.thesarfo.lexchange.model.dto.response.user.UserResponseDto;
import dev.thesarfo.lexchange.model.enums.UserRole;
import dev.thesarfo.lexchange.model.error.ErrorMessages;
import dev.thesarfo.lexchange.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponseDto registerUser(UserRegisterRequest userRegisterRequest) {
        if (userRepository.existsByEmail(userRegisterRequest.email())){
            throw new UserAlreadyExistsException(ErrorMessages.USER_EMAIL_ALREADY_EXISTS + userRegisterRequest.email());
        }

        User user = User.builder()
                .email(userRegisterRequest.email())
                .username(userRegisterRequest.username())
                .password(passwordEncoder.encode(userRegisterRequest.password()))
                .userRole(UserRole.USER)
                .build();
        User savedUser = userRepository.save(user);

        return createUserAndReturnUserResponse(savedUser);
    }

    private static UserResponseDto createUserAndReturnUserResponse(User savedUser) {
        return new UserResponseDto(
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getUsername(),
                LocalDateTime.now(),
                savedUser.getUserRole()
        );
    }
}
