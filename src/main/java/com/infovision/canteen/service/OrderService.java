package com.infovision.canteen.service;

import java.util.List;
import java.util.UUID;

import com.infovision.canteen.dto.Order.OrderDto;
import com.infovision.canteen.exception.OrderException;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.order.Orders;
import com.infovision.canteen.model.order.TopSellingOrders;

public interface OrderService {

	String orderItem(OrderDto orderDto) throws OrderException, Exception;

	String cancelOrderItem(UUID orderId)throws OrderException;

	List<OrderCartItem> getEmpOrders(UUID empId)throws OrderException;

	List<OrderCartItem> getAllOrders()throws OrderException;

	List<OrderCartItem> getRestaurantOrders(UUID restId) throws OrderException;

	List<TopSellingOrders> topSellingOrders()throws OrderException;

}
