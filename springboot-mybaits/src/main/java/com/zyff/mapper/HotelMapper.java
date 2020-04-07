package com.zyff.mapper;

import java.util.List;

import com.zyff.domain.Hotel;

public interface HotelMapper {
	public List<Hotel> findByCountry(String country);
}
