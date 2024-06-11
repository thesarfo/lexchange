package dev.thesarfo.lexchange.service.profile;

import dev.thesarfo.lexchange.entity.user.UserProfile;

public interface ProfileService {
    UserProfile retrieveProfile(String username);
}
