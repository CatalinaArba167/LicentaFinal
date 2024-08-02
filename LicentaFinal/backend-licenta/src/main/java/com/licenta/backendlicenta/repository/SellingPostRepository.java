package com.licenta.backendlicenta.repository;

import com.licenta.backendlicenta.domain.SellingPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SellingPostRepository extends JpaRepository<SellingPost, UUID> {

    @Query("SELECT s FROM SellingPost s WHERE s.owner.id = :loggedInUserId ORDER BY s.postDate DESC")
    List<SellingPost> findByOwnerIdOrderByPostDate(@Param("loggedInUserId") UUID loggedInUserId);

    @Query("SELECT s FROM SellingPost s WHERE s.owner.id != :loggedInUserId")
    List<SellingPost> findDistinctByOwnerId(@Param("loggedInUserId") UUID loggedInUserId);

//    @Query("SELECT s FROM SellingPost s WHERE s.id != :sellingPostId")
//    Optional<SellingPost> findById(@Param("sellingPostId") UUID sellingPostId);

}
