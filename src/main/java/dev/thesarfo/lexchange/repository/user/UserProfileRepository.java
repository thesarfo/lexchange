package dev.thesarfo.lexchange.repository.user;

import dev.thesarfo.lexchange.entity.user.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}