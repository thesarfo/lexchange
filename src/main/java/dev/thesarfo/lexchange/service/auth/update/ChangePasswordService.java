package dev.thesarfo.lexchange.service.auth.update;

import dev.thesarfo.lexchange.entity.user.User;
import dev.thesarfo.lexchange.model.dto.request.user.ChangePasswordRequest;
import dev.thesarfo.lexchange.model.error.ErrorMessages;
import dev.thesarfo.lexchange.model.success.SuccessMessages;
import dev.thesarfo.lexchange.repository.user.UserRepository;
import dev.thesarfo.lexchange.util.GeneralUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

    private final UserRepository userRepository;
    private final GeneralUtil generalUtil;
    private final PasswordEncoder passwordEncoder;

    public String changePassword(String id, ChangePasswordRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorMessages.USER_NOT_FOUND));

        generalUtil.checkIfIncomingOldPasswordIsNotEqualToTheExistingPassword(request, user);
        generalUtil.checkIfIncomingNewPasswordEqualsExistingPassword(request, user);
        generalUtil.checkIfIncomingOldPasswordIsEqualToIncomingNewPassword(request);

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
        return SuccessMessages.PASSWORD_CHANGED;
    }

}
