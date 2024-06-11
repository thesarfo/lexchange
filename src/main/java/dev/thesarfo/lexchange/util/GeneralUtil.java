package dev.thesarfo.lexchange.util;

import dev.thesarfo.lexchange.entity.user.UserProfile;
import dev.thesarfo.lexchange.model.dto.request.UpdateProfileRequest;

public class GeneralUtil {

    private GeneralUtil(){
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
        if (profile.profilePhoto() != null){
            existingProfile.setProfilePhoto(profile.profilePhoto());
        }
    }
}
