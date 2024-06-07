package dev.thesarfo.lexchange.mapper.user;

import dev.thesarfo.lexchange.entity.user.User;
import dev.thesarfo.lexchange.model.dto.response.user.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private UserMapper(){
    }

    public static UserResponseDto convertToUserDto(User user){
        return new UserResponseDto(
                user.getId(),
                user.getEmail(),
                user.getUsername(),
                user.getCreatedAt(),
                user.getUserRole()
        );
    }
}
