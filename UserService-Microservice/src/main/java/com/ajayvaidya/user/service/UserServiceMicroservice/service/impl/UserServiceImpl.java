package com.ajayvaidya.user.service.UserServiceMicroservice.service.impl;

import com.ajayvaidya.user.service.UserServiceMicroservice.entitiy.Hotel;
import com.ajayvaidya.user.service.UserServiceMicroservice.entitiy.Rating;
import com.ajayvaidya.user.service.UserServiceMicroservice.entitiy.User;
import com.ajayvaidya.user.service.UserServiceMicroservice.exception.ResourceNotFoundException;
import com.ajayvaidya.user.service.UserServiceMicroservice.externalservice.HotelService;
import com.ajayvaidya.user.service.UserServiceMicroservice.repository.UserRepository;
import com.ajayvaidya.user.service.UserServiceMicroservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with given id not found on server: "+userId));
        Rating[] ratingList = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getUserId(),Rating[].class);
        System.out.println("ratings: "+ratingList);
        List<Rating> ratings = Arrays.asList(ratingList);
        List<Rating> ratingListWithHotelInfo = ratings.stream().map(rating -> {
//            ResponseEntity<Hotel> hotelResponseEntity = restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//            logger.info("Response status: {},{}", hotelResponseEntity.getStatusCode(), hotelResponseEntity.getStatusCodeValue());
            rating.setHotel(hotel);

            return rating;
        }).collect(Collectors.toList());
        user.setRatingList(ratingListWithHotelInfo);
        return user;
    }

    @Override
    public String deleteUserById(String userId) {
        userRepository.deleteById(userId);
        return "User Deleted successfully!";
    }
}
