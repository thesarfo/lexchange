package dev.thesarfo.lexchange.controller.profile;

import dev.thesarfo.lexchange.model.success.SuccessMessages;
import dev.thesarfo.lexchange.service.profile.ProfileServiceImpl;
import dev.thesarfo.lexchange.util.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
public class RetrieveProfileController {

    private final ProfileServiceImpl profileService;

    @GetMapping("/{username}")
    public ResponseEntity<Object> retrieveProfile(@PathVariable String username) {
        return ResponseHandler.response(SuccessMessages.PROFILE_RETRIEVED, HttpStatus.OK,
                profileService.retrieveProfile(username));
    }
}
