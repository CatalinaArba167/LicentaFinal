package com.licenta.backendlicenta.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "selling_picture")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
public class SellingPicture extends BaseEntity{
    @Lob
    @Column(name = "picture", columnDefinition = "BYTEA")
    private byte[] picture;

    @ManyToOne
    @JoinColumn(name = "selling_post_id", referencedColumnName = "id")
    private SellingPost sellingPost;
}