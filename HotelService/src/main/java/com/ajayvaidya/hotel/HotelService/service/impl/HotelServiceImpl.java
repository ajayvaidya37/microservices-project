package com.ajayvaidya.hotel.HotelService.service.impl;

import com.ajayvaidya.hotel.HotelService.entity.Hotel;
import com.ajayvaidya.hotel.HotelService.exception.ResourceNotFoundException;
import com.ajayvaidya.hotel.HotelService.repository.HotelRepository;
import com.ajayvaidya.hotel.HotelService.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {
    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        String hotelId = UUID.randomUUID().toString();
        hotel.setId(hotelId);
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel getHotel(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(()-> new ResourceNotFoundException("hotel with given id not found" + hotelId));
    }
}
