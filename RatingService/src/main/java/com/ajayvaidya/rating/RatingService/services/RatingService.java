package com.ajayvaidya.rating.RatingService.services;

import com.ajayvaidya.rating.RatingService.entity.Rating;

import java.util.List;

public interface RatingService {

    Rating saveRating(Rating rating);

    List<Rating> getAllRatings();

    List<Rating> getRatingsByUserId(String userId);

    List<Rating> getRatingByHotelId(String hotelId);

}
