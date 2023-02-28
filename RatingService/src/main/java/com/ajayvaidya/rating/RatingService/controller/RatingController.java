package com.ajayvaidya.rating.RatingService.controller;

import com.ajayvaidya.rating.RatingService.entity.Rating;
import com.ajayvaidya.rating.RatingService.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {
    @Autowired
    private RatingService ratingService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating){
       return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.saveRating(rating));
    }


    @GetMapping
    public ResponseEntity<List<Rating>> getAllRatings(){
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/users/{userId}")
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('ADMIN')")
    public ResponseEntity<List<Rating>> getRatingsByUserId(@PathVariable("userId") String userId){
        return ResponseEntity.ok(ratingService.getRatingsByUserId(userId));
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('ADMIN')")
    @GetMapping("/hotels/{hostelId}")
    public ResponseEntity<List<Rating>> getRatingsByHotelId(@PathVariable("hostelId") String hostelId){
        return ResponseEntity.ok(ratingService.getRatingByHotelId(hostelId));
    }
}
