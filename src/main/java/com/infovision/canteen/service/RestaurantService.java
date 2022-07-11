package com.infovision.canteen.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.infovision.canteen.dto.restaurant.RestaurantItemDto;
import com.infovision.canteen.dto.restaurant.RestaurantProfileDto;
import com.infovision.canteen.exception.RestaurantException;

public interface RestaurantService {

	RestaurantProfileDto addRestaurant(RestaurantProfileDto restaurantProfileDto) throws RestaurantException, IOException;

	RestaurantProfileDto editRestaurant(RestaurantProfileDto restaurantProfileDto, UUID id);
	
	Object getRestaurant();

	String removeRestaurant(UUID restaurantProfileId) throws Exception;

}
