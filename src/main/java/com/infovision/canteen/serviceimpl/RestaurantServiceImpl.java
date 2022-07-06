package com.infovision.canteen.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.restaurant.RestaurantProfileDto;
import com.infovision.canteen.exception.RestaurantException;
import com.infovision.canteen.model.restaurant.Restaurant;
import com.infovision.canteen.model.restaurant.RestaurantProfile;
import com.infovision.canteen.repository.RestaurantProfileRepository;
import com.infovision.canteen.repository.RestaurantRepository;
import com.infovision.canteen.service.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService{

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantProfileRepository restaurantProfileRepository;
	

	
	@Override
	public RestaurantProfileDto addRestaurant(RestaurantProfileDto restaurantProfileDto) throws RestaurantException, IOException {
		// TODO Auto-generated method stub
		
		List<Restaurant> restaurants = restaurantRepository.findAll();
		 	
		for(Restaurant restaurant: restaurants)
		{
			if(restaurant.getRestaurantProfile().getRestaurantName().equals(restaurantProfileDto.getRestaurantName())
					&& restaurant.getRestaurantProfile().getCity().equals(restaurantProfileDto.getCity()))
			{
				throw new RestaurantException("Restaurant already exists with this name "+ restaurantProfileDto.getRestaurantName());
			}
		}
		
		Restaurant restaurant=new Restaurant();
		
		RestaurantProfile restaurantProfile= new RestaurantProfile();
		
		
		restaurantProfile.setRestaurantName(restaurantProfileDto.getRestaurantName());
		restaurantProfile.setEmail(restaurantProfile.getEmail());
		restaurantProfile.setMobileNumber(restaurantProfile.getMobileNumber());
		restaurantProfile.setPassword(restaurantProfile.getPassword());
		restaurantProfile.setCountry(restaurantProfile.getCountry());
		restaurantProfile.setState(restaurantProfile.getState());
		restaurantProfile.setCity(restaurantProfile.getCity());
		restaurantProfile.setImageUrl(restaurantProfileDto.getImageUrl());
		
		restaurant.setRestaurantProfile(restaurantProfile);
		
		restaurantProfileRepository.saveAndFlush(restaurantProfile);
		
		restaurantRepository.save(restaurant);
		
		editRestaurant(restaurantProfileDto,restaurant.getRestaurantid());
		
		return restaurantProfileDto;
	}

	@Override
	public RestaurantProfileDto editRestaurant(RestaurantProfileDto restaurantProfileDto, UUID id) {
		// TODO Auto-generated method stub
		
		if(restaurantRepository.existsById(id))
		{
			Restaurant restaurant= restaurantRepository.getOne(id);
			
			restaurant.getRestaurantProfile().setCity(restaurantProfileDto.getCity());
			restaurant.getRestaurantProfile().setCountry(restaurantProfileDto.getCountry());
			restaurant.getRestaurantProfile().setEmail(restaurantProfileDto.getEmail());
			restaurant.getRestaurantProfile().setMobileNumber(restaurantProfileDto.getMobileNumber());
			restaurant.getRestaurantProfile().setPassword(restaurantProfileDto.getPassword());
			restaurant.getRestaurantProfile().setRestaurantName(restaurantProfileDto.getRestaurantName());
			restaurant.getRestaurantProfile().setState(restaurantProfileDto.getState());
			restaurant.getRestaurantProfile().setImageUrl(restaurantProfileDto.getImageUrl());
			
			restaurantRepository.save(restaurant);
			
		}
		
		return restaurantProfileDto;
	}

}
