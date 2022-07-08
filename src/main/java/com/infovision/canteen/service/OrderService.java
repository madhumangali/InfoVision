package com.infovision.canteen.service;

import java.util.List;
import java.util.UUID;

import com.infovision.canteen.dto.Order.OrderDto;
import com.infovision.canteen.exception.OrderException;
import com.infovision.canteen.model.order.Orders;

public interface OrderService {

	String orderItem(OrderDto orderDto) throws OrderException;

	String cancelOrderItem(UUID orderId)throws OrderException;

	List<Orders> getEmpOrders(UUID empId)throws OrderException;

	List<Orders> getAllOrders()throws OrderException;

	List<Orders> getRestaurantOrders(UUID restId) throws OrderException;

	List<Orders> topSellingOrders()throws OrderException;

}
