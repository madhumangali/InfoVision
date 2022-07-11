package com.infovision.canteen.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.model.delivery.Delivery;
import com.infovision.canteen.model.order.DeliveryOrderStatus;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.order.OrderStatus;
import com.infovision.canteen.model.order.Orders;
import com.infovision.canteen.repository.CartItemRepository;
import com.infovision.canteen.repository.DeliveryRepository;
import com.infovision.canteen.repository.OrderCartItemRepository;
import com.infovision.canteen.repository.OrderRepository;
import com.infovision.canteen.repository.PaymentRepository;
import com.infovision.canteen.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private OrderCartItemRepository orderCartItemRepository;

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Orders> assignOrders() {
		// TODO Auto-generated method stub
		
		List<Orders> orders= orderRepository.findByTIme(LocalTime.now(),LocalDate.now());
		
		List<Delivery> deliveryBoys=deliveryRepository.findDeliveryBoys();
		
		Iterator<Orders> orders1=orders.iterator();
		Iterator<Delivery> delivery1=deliveryBoys.iterator();
		
		while(orders1.hasNext() && delivery1.hasNext())
		{
			orders1.next().setDelivery(delivery1.next());
			orders1.next().setDeliveryOrderStatus(DeliveryOrderStatus.PENDING);
			orderRepository.save(orders1.next());
			
			List<OrderCartItem> orderCartItems=orderCartItemRepository.findByOrderId(orders1.next().getOrderId());
			
			for(OrderCartItem cartItem:orderCartItems)
			{
				cartItem.setDeliveryOrderStatus(OrderStatus.ACCEPT);
				orderCartItemRepository.save(cartItem);
				
			}
			
		}
		
		
		return orders;
	}

}
