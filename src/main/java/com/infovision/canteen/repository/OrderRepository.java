package com.infovision.canteen.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.model.order.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID>{

	@Query("select s from Orders s where s.employee.empId=:empId")
	List<Orders> getByEmployee(UUID empId);

	@Query("select s from Orders s where s.restaurantItem.restaurant.restaurantid=:restId")
	List<Orders> getByRestaurant(UUID restId);

	@Query("SELECT s.cart,COUNT(s.cart.restaurantItem.itemId) from Orders s GROUP BY s.cart.restaurantItem.itemId where s.date=:date")
	List<Orders> getByDate(LocalDate date);

}
