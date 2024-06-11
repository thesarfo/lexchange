package dev.thesarfo.lexchange.service.profile;

import dev.thesarfo.lexchange.entity.user.UserProfile;
import dev.thesarfo.lexchange.exception.profile.ProfileNotFoundException;
import dev.thesarfo.lexchange.model.dto.request.profile.UpdateProfileRequest;
import dev.thesarfo.lexchange.model.error.ErrorMessages;
import dev.thesarfo.lexchange.repository.user.UserProfileRepository;
import dev.thesarfo.lexchange.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static dev.thesarfo.lexchange.util.GeneralUtil.updateProfileFields;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;

    @Override
    public UserProfile retrieveProfile(String username) {
        return profileRepository.findByUsername(username)
                .orElseThrow(() -> new ProfileNotFoundException(ErrorMessages.PROFILE_NOT_FOUND + username));
    }

    @Override
    public UserProfile updateProfile(Long id, UpdateProfileRequest profile) {
        UserProfile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(ErrorMessages.PROFILE_NOT_FOUND));

        updateProfileFields(profile, existingProfile);
        userRepository.save(existingProfile.getUser());
        return profileRepository.save(existingProfile);
    }

}
