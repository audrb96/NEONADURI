package neonaduri.api.repository;


import neonaduri.domain.Spot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface SpotRepository extends JpaRepository<Spot, Long>, SpotRepositoryCustom {

    Spot findSpotBySpotId(Long spotId); // Optional X. 빈 값 있지 않음.

    @Query("select s from Spot s join fetch s.reviews")
    Spot findDetailsSpotBySpotId(Long spotId);

    List<Spot> findSpotBySpotName(String spotName);
}
