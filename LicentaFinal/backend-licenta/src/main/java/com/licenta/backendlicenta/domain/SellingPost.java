package com.licenta.backendlicenta.domain;

import com.licenta.backendlicenta.enums.Availability;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "selling_post")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
@SuperBuilder
public class SellingPost extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @Enumerated(EnumType.STRING)
    private Availability available;

    private String title;

    private String description;

    @Column(name = "post_date")
    private LocalDateTime postDate;

//    @OneToMany(mappedBy = "sellingPost", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<SellingPicture> sellingPictures;
}
