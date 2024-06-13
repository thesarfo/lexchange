package dev.thesarfo.lexchange.service.profile;

import dev.thesarfo.lexchange.entity.user.UserProfile;
import dev.thesarfo.lexchange.model.dto.request.profile.UpdateProfileRequest;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.web.multipart.MultipartFile;

public interface ProfileService {
    UserProfile retrieveProfile(String username);
    UserProfile updateProfile(Long id, UpdateProfileRequest profile);
    UserProfile uploadPfp(Long id, String key, MultipartFile file);
}
