package neonaduri.api.service;

import lombok.RequiredArgsConstructor;
import neonaduri.api.repository.SpotRepository;
import neonaduri.domain.Spot;
import neonaduri.domain.Tag;
import neonaduri.dto.response.ReviewTagsRes;
import neonaduri.dto.response.SpotDetailsRes;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    public SpotDetailsRes getSpotDetailsInfo(Long spotId) {

        /* 1. fetch join을 통해 review까지 가져와서 */
        Spot spot = spotRepository.findDetailsSpotBySpotId(spotId);

        /* 2. review 목록에서 리뷰 내용고 태그를 꺼내와서 ReviewTagsRes로 정리해준다. */
        List<ReviewTagsRes> reviews = spot.getReviews().stream()
                .map(review -> ReviewTagsRes.builder()
                        .reviewContent(review.getReviewContent())
                        .reviewImage(review.getReviewImage())
                        .tagContents(review.getTags().toString())
                        .build())
                .sorted()
                .collect(Collectors.toList());

        /* 3. 마지막으로 SpotDetailsRes로 반환시켜준다. */
        return SpotDetailsRes.builder()
                .spotContent(spot.getSpotContent())
                .spotName(spot.getSpotName())
                .spotImage(spot.getSpotImage())
                .reviewContainsTags(reviews)
                .build();
    }
}