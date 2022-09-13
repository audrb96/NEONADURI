package neonaduri.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Table(name = "spot")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id", nullable = false)
    private Long spotId;

    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "class_id")
    private Long classId;

    @OneToMany(mappedBy = "spotId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> reviews = new HashSet<>();

    @Column(name = "spot_name", nullable = false)
    private String spotName;

    @Column(name = "lat", nullable = false)
    private float lat;

    @Column(name = "lng", nullable = false)
    private float lng;

    @Column(name = "tel", nullable = false)
    private String tel;

    @Column(name = "spot_image", nullable = false)
    private String spotImage;

    @Column(name = "spot_content", nullable = false)
    private String spotContent;
}
