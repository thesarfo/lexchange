package dev.thesarfo.lexchange.util;

import dev.thesarfo.lexchange.entity.user.User;
import dev.thesarfo.lexchange.entity.user.UserProfile;
import dev.thesarfo.lexchange.exception.user.UserNotFoundException;
import dev.thesarfo.lexchange.model.dto.request.profile.UpdateProfileRequest;
import dev.thesarfo.lexchange.model.dto.request.user.ChangePasswordRequest;
import dev.thesarfo.lexchange.model.error.ErrorMessages;
import dev.thesarfo.lexchange.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GeneralUtil {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public static void updateProfileFields(UpdateProfileRequest profile, UserProfile existingProfile) {
        if (profile.username() != null){
            existingProfile.setUsername(profile.username());
            existingProfile.getUser().setUsername(profile.username());
        }
        if (profile.email() != null){
            existingProfile.setEmail(profile.email());
            existingProfile.getUser().setEmail(profile.email());
        }
        if (profile.bio() != null){
            existingProfile.setBio(profile.bio());
        }
//        if (profile.profilePhoto() != null){
//            existingProfile.setProfilePhoto(profile.profilePhoto());
//        }
    }

    public void checkIfIncomingNewPasswordEqualsExistingPassword(ChangePasswordRequest request, User user) {
        if (request.newPassword().equals(user.getPassword())){
            throw new BadCredentialsException(ErrorMessages.NEW_PASSWORD_SAME_AS_OLD);
        }
    }

    public void checkIfIncomingOldPasswordIsNotEqualToTheExistingPassword(ChangePasswordRequest request, User user) {
        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())){
            throw new BadCredentialsException(ErrorMessages.OLD_PASSWORD_INCORRECT);
        }
    }

    public void checkIfIncomingOldPasswordIsEqualToIncomingNewPassword(ChangePasswordRequest request){
        if (request.oldPassword().equals(request.newPassword())){
            throw new BadCredentialsException(ErrorMessages.OLD_AND_NEW_PASSWORD_EQUAL);
        }
    }

    public String getUsernameFromSecurityContext() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    public User getUserOrThrowException(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()-> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND + username));
    }

    public boolean isValidFile(MultipartFile file){
        long maxSize = 1024 * 1024 * 5; // 5MB

        List<String> allowedContentTypes = Arrays.asList(
                "image/*",
                "image/jpeg",
                "image/png"
        );
        return file.getSize() <= maxSize && allowedContentTypes.contains(file.getContentType());
    }
}
