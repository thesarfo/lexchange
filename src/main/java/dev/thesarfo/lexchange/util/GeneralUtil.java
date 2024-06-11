package dev.thesarfo.lexchange.util;

import dev.thesarfo.lexchange.entity.user.User;
import dev.thesarfo.lexchange.entity.user.UserProfile;
import dev.thesarfo.lexchange.model.dto.request.profile.UpdateProfileRequest;
import dev.thesarfo.lexchange.model.dto.request.user.ChangePasswordRequest;
import dev.thesarfo.lexchange.model.error.ErrorMessages;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class GeneralUtil {

    private final PasswordEncoder passwordEncoder;

    private GeneralUtil(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

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
}
