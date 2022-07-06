package com.infovision.canteen.service;

import java.util.List;
import java.util.UUID;

import com.infovision.canteen.dto.restaurant.RestaurantItemDto;
import com.infovision.canteen.exception.RestaurantItemException;

public interface RestaurantItemService {

	List<RestaurantItemDto> addItem(List<RestaurantItemDto> restaurantItemDtos,UUID RestId) throws RestaurantItemException;

	Object editItem(List<UUID> ids,List<RestaurantItemDto> restaurantItemDtos);

}
