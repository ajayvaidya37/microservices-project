package com.ajayvaidya.hotel.HotelService.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    @GetMapping
    public ResponseEntity<List<String>> getAllStaffs(){
        List<String> staffList = Arrays.asList("Ajay", "Vijay", "Ramesh", "Suresh");
        return ResponseEntity.ok(staffList);
    }
}
