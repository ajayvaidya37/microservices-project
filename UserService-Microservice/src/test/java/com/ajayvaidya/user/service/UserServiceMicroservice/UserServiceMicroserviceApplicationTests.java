package com.ajayvaidya.user.service.UserServiceMicroservice;

import com.ajayvaidya.user.service.UserServiceMicroservice.entitiy.Rating;
import com.ajayvaidya.user.service.UserServiceMicroservice.externalservice.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class UserServiceMicroserviceApplicationTests {

	@Autowired
	private RatingService ratingService;

	@Test
	void contextLoads() {
	}

//	@Test
//	void tesCreateRatingFromFeignRatingClient(){
//		Rating rating = Rating.builder()
//				.rating(8)
//				.feedback("test rating")
//				.hotelId("")
//				.build();
//
//		ResponseEntity<Rating> ratingResponseEntity = ratingService.saveRating(rating);
//		System.out.println(ratingResponseEntity.getStatusCode());
//		System.out.println(ratingResponseEntity.getBody());
//	}
}
