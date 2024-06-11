package dev.thesarfo.lexchange.controller.auth;

import dev.thesarfo.lexchange.model.dto.request.user.ChangePasswordRequest;
import dev.thesarfo.lexchange.model.success.SuccessMessages;
import dev.thesarfo.lexchange.service.auth.update.ChangePasswordService;
import dev.thesarfo.lexchange.util.ResponseHandler;
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
@RequestMapping("/api/v1/auth/change-password")
public class ChangePasswordController {

    private final ChangePasswordService cps;

    @PatchMapping("/{id}")
    public ResponseEntity<Object> changePassword(@PathVariable String id, @Valid @RequestBody ChangePasswordRequest request){
        return ResponseHandler.response(SuccessMessages.PASSWORD_CHANGED, HttpStatus.OK, cps.changePassword(id, request));
    }

}
