package dev.thesarfo.lexchange.service.auth.login;

import dev.thesarfo.lexchange.model.dto.request.user.UserLoginRequest;
import dev.thesarfo.lexchange.model.dto.response.user.JwtResponse;
import dev.thesarfo.lexchange.model.error.ErrorMessages;
import dev.thesarfo.lexchange.service.user.UserDetailsImpl;
import dev.thesarfo.lexchange.service.user.UserDetailsServiceImpl;
import dev.thesarfo.lexchange.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserLoginServiceImpl implements UserLoginService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;

    public JwtResponse signin(UserLoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String jwt = jwtUtil.generateJwtToken(authentication);


            List<String> roles = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();


//            return ResponseEntity.ok(new JwtResponse(jwt,
//                    userDetails.getId(),
//                    userDetails.getUsername(),
//                    roles));
            return new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    roles);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(ErrorMessages.INVALID_CREDENTIALS, e);
        }

    }
}
