package com.infovision.canteen.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.restaurant.RestaurantItemDto;
import com.infovision.canteen.exception.RestaurantItemException;
import com.infovision.canteen.model.restaurant.ItemStatus;
import com.infovision.canteen.model.restaurant.Restaurant;
import com.infovision.canteen.model.restaurant.RestaurantItem;
import com.infovision.canteen.repository.RestaurantItemRepository;
import com.infovision.canteen.repository.RestaurantRepository;
import com.infovision.canteen.service.RestaurantItemService;

@Service
public class RestaurantItemServiceImpl implements RestaurantItemService {

	@Autowired
	private RestaurantItemRepository restaurantItemRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Override
	public List<RestaurantItemDto> addItem(List<RestaurantItemDto> restaurantItemDtos, UUID RestId)
			throws RestaurantItemException {
		// TODO Auto-generated method stub

		Restaurant restaurant = restaurantRepository.getOne(RestId);

		List<UUID> ids = new ArrayList<>();

		if (restaurantRepository.existsById(RestId)) {

			for (RestaurantItemDto restaurantItemDto : restaurantItemDtos) {
				RestaurantItem restaurantItem = new RestaurantItem();

				restaurantItem.setItemDesc(restaurantItemDto.getItemDesc());
				restaurantItem.setItemName(restaurantItem.getItemName());
				restaurantItem.setItemprice(restaurantItemDto.getItemprice());
				restaurantItem.setRating(restaurantItemDto.getRating());
				restaurantItem.setRestaurant(restaurant);
				restaurantItem.setStatus(ItemStatus.AVAILABLE);
				restaurantItem.setImageUrl(restaurantItemDto.getImageUrl());
				restaurantItem.setRestaurant(restaurant);
				restaurantItemRepository.save(restaurantItem);
				ids.add(restaurantItem.getItemId());

			}

		} else
			throw new RestaurantItemException("Restaurant details not found");

		editItem(ids, restaurantItemDtos);

		return restaurantItemDtos;
	}

	@Override
	public Object editItem(List<UUID> ids, List<RestaurantItemDto> restaurantItemDtos) {
		// TODO Auto-generated method stub

		List<RestaurantItem> restaurantItems = new ArrayList<>();

		for (int i = 0; i < ids.size() && i < restaurantItemDtos.size(); i++) {
			if (restaurantItemRepository.existsById(ids.get(i))) {
				RestaurantItem restaurantItem = restaurantItemRepository.getOne(ids.get(i));

				restaurantItem.setItemName(restaurantItemDtos.get(i).getItemName());
				restaurantItem.setItemprice(restaurantItemDtos.get(i).getItemprice());

				restaurantItemRepository.save(restaurantItem);

				restaurantItems.add(restaurantItem);
			}
		}

		restaurantItemRepository.saveAll(restaurantItems);

		return null;
	}

}
