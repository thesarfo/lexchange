package dev.thesarfo.lexchange.service.profile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import dev.thesarfo.lexchange.entity.user.UserProfile;
import dev.thesarfo.lexchange.exception.aws.InvalidFileException;
import dev.thesarfo.lexchange.exception.profile.ProfileNotFoundException;
import dev.thesarfo.lexchange.model.dto.request.profile.UpdateProfileRequest;
import dev.thesarfo.lexchange.model.error.ErrorMessages;
import dev.thesarfo.lexchange.repository.user.UserProfileRepository;
import dev.thesarfo.lexchange.repository.user.UserRepository;
import dev.thesarfo.lexchange.service.aws.FileUploadService;
import dev.thesarfo.lexchange.util.GeneralUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static dev.thesarfo.lexchange.util.GeneralUtil.updateProfileFields;

/**
 * <p>This class contains various methods being used to manipulate the user's profile</p>
 *
 * @author Ernest Sarfo
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService{

    private final UserProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final FileUploadService fileUploadService;
    private final GeneralUtil generalUtil;
    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @Override
    public UserProfile retrieveProfile(String username) {
        return profileRepository.findByUsername(username)
                .orElseThrow(() -> new ProfileNotFoundException(ErrorMessages.PROFILE_NOT_FOUND + username));
    }

    @Override
    public UserProfile updateProfile(Integer id, UpdateProfileRequest profile) {
        UserProfile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(ErrorMessages.PROFILE_NOT_FOUND));

        updateProfileFields(profile, existingProfile);
        userRepository.save(existingProfile.getUser());
        return profileRepository.save(existingProfile);
    }

    @Override
    public UserProfile uploadPfp(Integer id, String key, MultipartFile file) {
        if (!generalUtil.isValidFile(file)) {
            throw new InvalidFileException(ErrorMessages.INVALID_FILE);
        }
        try{
            fileUploadService.uploadFile(key, file);
        } catch (IOException e){
            throw new AmazonS3Exception(ErrorMessages.FILE_ERROR);
        }
        UserProfile profile = setPfpToUploadedImg(id, key);
        return profileRepository.save(profile);
    }

    private UserProfile setPfpToUploadedImg(Integer id, String key) {
        UserProfile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ProfileNotFoundException(ErrorMessages.PROFILE_NOT_FOUND));
        String url = amazonS3.getUrl(bucketName, key).toString();

        profile.setProfilePhoto(url);
        return profile;
    }

}
