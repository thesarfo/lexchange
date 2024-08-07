package dev.thesarfo.lexchange.service.auth.register;

import dev.thesarfo.lexchange.entity.user.User;
import dev.thesarfo.lexchange.entity.user.UserProfile;
import dev.thesarfo.lexchange.exception.user.UserAlreadyExistsException;
import dev.thesarfo.lexchange.model.dto.request.user.UserRegisterRequest;
import dev.thesarfo.lexchange.model.dto.response.user.UserResponseDto;
import dev.thesarfo.lexchange.model.enums.UserRole;
import dev.thesarfo.lexchange.model.error.ErrorMessages;
import dev.thesarfo.lexchange.repository.user.UserProfileRepository;
import dev.thesarfo.lexchange.repository.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserRegisterServiceImpl implements UserRegisterService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserProfileRepository userProfileRepository;


    /**
     * <p>This method registers new users onto the system </p>
     *
     * @param userRegisterRequest the minimum details a user should provide during registration
     * @return the details of the registered user, if successful
     *
     * @since 1.0
     */
    @Override
    @Transactional
    public UserResponseDto registerUser(UserRegisterRequest userRegisterRequest) {
        if (userRepository.existsByEmail(userRegisterRequest.email()) ||
                userRepository.existsByUsername(userRegisterRequest.username())){
            throw new UserAlreadyExistsException(ErrorMessages.USER_ALREADY_EXISTS);
        }

        User user = User.builder()
                .email(userRegisterRequest.email())
                .username(userRegisterRequest.username())
                .password(passwordEncoder.encode(userRegisterRequest.password()))
                .createdAt(LocalDateTime.now())
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
