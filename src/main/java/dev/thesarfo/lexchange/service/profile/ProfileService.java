package dev.thesarfo.lexchange.service.profile;

import dev.thesarfo.lexchange.entity.user.UserProfile;
import dev.thesarfo.lexchange.model.dto.request.profile.UpdateProfileRequest;

public interface ProfileService {
    UserProfile retrieveProfile(String username);
    UserProfile updateProfile(Integer id, UpdateProfileRequest profile);
}
