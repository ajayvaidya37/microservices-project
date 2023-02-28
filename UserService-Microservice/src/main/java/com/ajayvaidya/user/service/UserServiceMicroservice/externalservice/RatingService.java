package com.ajayvaidya.user.service.UserServiceMicroservice.externalservice;

import com.ajayvaidya.user.service.UserServiceMicroservice.entitiy.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "RATING-SERVICE")
@Service
public interface RatingService {

    @PostMapping("/ratings")
    ResponseEntity<Rating> saveRating(Rating rating);

    @PutMapping("/ratings/{ratingId}")
    ResponseEntity<Rating> updateRating(@PathVariable String ratingId, Rating rating);
}
