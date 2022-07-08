package com.infovision.canteen.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.restaurant.RestaurantItem;

@Repository
public interface RestaurantItemRepository extends JpaRepository<RestaurantItem, UUID>{

	@Query("select s from RestaurantItem s where s.restaurant.restaurantid=:restaurantid")
	List<RestaurantItem> findByRestaurant(UUID restaurantid);


	@Query("select s from RestaurantItem s where s.restaurant.restaurantid=:restId AND s.itemName=:itemName" )
	RestaurantItem getRestItem(UUID restId,String itemName);

}
