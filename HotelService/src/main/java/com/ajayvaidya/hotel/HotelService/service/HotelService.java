package com.ajayvaidya.hotel.HotelService.service;

import com.ajayvaidya.hotel.HotelService.entity.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAll();

    Hotel getHotel(String hotelId);

}
