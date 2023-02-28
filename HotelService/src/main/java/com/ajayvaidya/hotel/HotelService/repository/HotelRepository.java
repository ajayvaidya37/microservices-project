package com.ajayvaidya.hotel.HotelService.repository;

import com.ajayvaidya.hotel.HotelService.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel,String> {
}
