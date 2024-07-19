package dev.thesarfo.lexchange.controller.profile;

import dev.thesarfo.lexchange.model.dto.request.profile.UpdateProfileRequest;
import dev.thesarfo.lexchange.model.success.SuccessMessages;
import dev.thesarfo.lexchange.service.profile.ProfileServiceImpl;
import dev.thesarfo.lexchange.util.ResponseHandler;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class UpdateProfileController {

    private final ProfileServiceImpl profileService;

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateProfile(@PathVariable Integer id, @Valid @RequestBody UpdateProfileRequest request){
        return ResponseHandler.response(SuccessMessages.PROFILE_UPDATED,
                HttpStatus.OK, profileService.updateProfile(id, request));
    }
}
