package com.licenta.backendlicenta.repository;


import com.licenta.backendlicenta.domain.SellingPicture;
import com.licenta.backendlicenta.domain.SellingPost;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellingPictureRepository extends JpaRepository<SellingPicture, UUID>{

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO selling_picture (id, picture, selling_post_id) VALUES (:id, CAST(:picture AS bytea), :sellingPostId)", nativeQuery = true)
    void saveSellingPicture(@Param("id") UUID id, @Param("picture") byte[] picture, @Param("sellingPostId") UUID sellingPostId);

    @Query(value ="SELECT p.picture FROM selling_picture p WHERE p.selling_post_id = ?1",nativeQuery = true)
    Optional<List<byte[]>> findBySellingPostId(UUID sellingPostId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM selling_picture p WHERE p.selling_post_id = ?1", nativeQuery = true)
    void deleteBySellingPostId(UUID sellingPostId);
}
