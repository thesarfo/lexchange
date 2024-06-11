package dev.thesarfo.lexchange.service.profile;

import dev.thesarfo.lexchange.entity.user.UserProfile;
import dev.thesarfo.lexchange.repository.user.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private final UserProfileRepository profileRepository;

    @Override
    public UserProfile retrieveProfile(String username) {
        return profileRepository.findByUsername(username);
    }
}
