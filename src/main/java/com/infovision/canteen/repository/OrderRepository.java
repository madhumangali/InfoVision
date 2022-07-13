package com.infovision.canteen.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.order.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, UUID>{

	@Query("select s from Orders s where s.employee.empId=:empId")
	List<Orders> getByEmployee(UUID empId);

	@Query("select s from Orders s where s.date=:localDate AND s.time<=:now AND s.delivery.deliveryId=:null")
	List<Orders> findByTIme(LocalTime now, LocalDate localDate);

	@Query("select s from Orders s where s.delivery.deliveryId=:deliveryId")
	Orders getOrderById(UUID deliveryId);

	@Query("select s from Orders s where s.employee.empId=:empId")
	List<Orders> getOrders(UUID empId);
	
//	@Query("select s from Orders s where s.cart.cartId=:cartId AND s.order.employeeOrderStatus = 'CONFIRM'")
//
//	List<Orders> getByCart(UUID cartId);

	
}
