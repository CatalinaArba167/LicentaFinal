package com.licenta.backendlicenta.repository;

import com.licenta.backendlicenta.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_table SET profile_picture = CAST(:profilePicture AS bytea) WHERE id = :userId", nativeQuery = true)
    void updateProfilePicture(@Param("userId") UUID userId, @Param("profilePicture") byte[] profilePicture);
}