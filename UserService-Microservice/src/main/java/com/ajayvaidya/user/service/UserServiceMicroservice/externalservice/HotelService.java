package com.ajayvaidya.user.service.UserServiceMicroservice.externalservice;

import com.ajayvaidya.user.service.UserServiceMicroservice.entitiy.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.Path;

@FeignClient(name = "HOTEL-SERVICE")
public interface HotelService {

    @GetMapping("hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") String hotelId);
}
