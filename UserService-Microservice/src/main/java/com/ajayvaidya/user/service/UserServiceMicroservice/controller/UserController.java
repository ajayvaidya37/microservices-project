package com.ajayvaidya.user.service.UserServiceMicroservice.controller;

import com.ajayvaidya.user.service.UserServiceMicroservice.entitiy.User;
import com.ajayvaidya.user.service.UserServiceMicroservice.payload.ApiResponse;
import com.ajayvaidya.user.service.UserServiceMicroservice.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    int retryCount = 1;
    @GetMapping("/{userId}")
//    @CircuitBreaker(name="ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
//    @Retry(name="ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId){
        System.out.println("retrying.....");
        logger.info("No of retries: {}",retryCount);
        retryCount++;
        User user = userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
    //creating fallback method for circuitbreaker
    public ResponseEntity<User> ratingHotelFallback(String userId, Exception exception){
        exception.printStackTrace();
       // logger.info("Fallback method is executed because of some service is down: {}",exception.getMessage());
       logger.info("Fallback method is executed due to rate Limiter: {}", exception.getMessage());
        User user = User.builder().email("dummy@ex.com").name("dummy").about("dummy created due to error").build();
        return ResponseEntity.ok(user);
    }
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUsers = userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }
}
