package com.licenta.backendlicenta.repository;

import com.licenta.backendlicenta.domain.ProfilePicture;
import com.licenta.backendlicenta.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfilePictureRepository extends JpaRepository<ProfilePicture, UUID> {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO profile_picture (id, profile_picture, user_id) VALUES (:id, CAST(:profilePicture AS bytea), :userId)", nativeQuery = true)
    void saveProfilePicture(@Param("id") UUID id, @Param("profilePicture") byte[] profilePicture, @Param("userId") UUID userId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE profile_picture p SET profile_picture = :profilePicture, user_id = :userId,id=:id WHERE p.id = :id", nativeQuery = true)
    void updateProfilePicture(@Param("id") UUID id, @Param("profilePicture") byte[] profilePicture, @Param("userId") UUID userId);


    @Query(value ="SELECT p.profile_picture FROM profile_picture p WHERE p.user_id = ?1",nativeQuery = true)
    Optional<byte[]> getProfilePictureByUserId(UUID userId);

    @Query(value ="SELECT p.id FROM profile_picture p WHERE p.user_id = ?1",nativeQuery = true)
    Optional<UUID> getProfilePictureIdByUserId(UUID userId);
}
