package com.infovision.canteen.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.Order.OrderDto;
import com.infovision.canteen.exception.OrderException;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.order.EmployeeOrderStatus;
import com.infovision.canteen.model.order.Orders;
import com.infovision.canteen.model.payment.Mode;
import com.infovision.canteen.model.payment.Payment;
import com.infovision.canteen.model.restaurant.RestaurantItem;
import com.infovision.canteen.repository.AdminRepository;
import com.infovision.canteen.repository.CartItemRepository;
import com.infovision.canteen.repository.CartRepository;
import com.infovision.canteen.repository.EmployeeRepository;
import com.infovision.canteen.repository.MenuItemRepository;
import com.infovision.canteen.repository.OrderRepository;
import com.infovision.canteen.repository.PaymentRepository;
import com.infovision.canteen.repository.RestaurantItemRepository;
import com.infovision.canteen.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RestaurantItemRepository restaurantItemRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public String orderItem(OrderDto orderDto) throws OrderException {
		// TODO Auto-generated method stub

		Orders order = new Orders();

		if (employeeRepository.existsById(orderDto.getEmpId()))
			order.setEmployee(employeeRepository.getOne(orderDto.getEmpId()));
		else
			throw new OrderException("Employee Not found");

		if (cartRepository.existsById(orderDto.getCartId())) {
			
			if (employeeRepository.getOne(orderDto.getEmpId()).getCart().getCartId().equals(orderDto.getCartId()))
				order.setCart(cartRepository.getOne(orderDto.getCartId()));
			else
				throw new OrderException("Cart not belongs current Employee");
			
		} else
			throw new OrderException("Cart Not found");
		
		Payment payment =new Payment();
		
		payment.setAmount(cartRepository.getOne(orderDto.getCartId()).getTotalamount());
		payment.setMode(Mode.CREDITCARD);

//		 InComplete
		
		paymentRepository.save(payment);
		
		order.setDate(LocalDate.now());
		order.setTime(LocalTime.now());
		order.setPayment(payment);
		
		order.setEmployeeOrderStatus(EmployeeOrderStatus.CONFIRM);
		
		return "Order Placed Successfully";
	}

	@Override
	public String cancelOrderItem(UUID orderId) throws OrderException {
		// TODO Auto-generated method stub
		
		if(orderRepository.existsById(orderId))
		{
			Orders order=orderRepository.getOne(orderId);
			
			order.setEmployeeOrderStatus(EmployeeOrderStatus.CANCEL);
		}
		else
			throw new OrderException("Order Not found");
		
		return "Order Cancelled";
	}

	@Override
	public List<Orders> getEmpOrders(UUID empId) throws OrderException {
		// TODO Auto-generated method stub
		
		if(employeeRepository.existsById(empId))
		{
			List<Orders> orders=orderRepository.getByEmployee(empId);
			
			if(orders.isEmpty())
				throw new OrderException("Orders list is empty");
			
			return orders;
			
		}
		else
			throw new OrderException("Employee Not found");
		
	}

	@Override
	public List<Orders> getAllOrders() throws OrderException {
		// TODO Auto-generated method stub
		
		List<Orders> orders=orderRepository.findAll();
		
		if(orders.isEmpty())
			throw new OrderException("Orders list is empty");
		
		return orders;

	}

	@Override
	public List<Orders> getRestaurantOrders(UUID restId) throws OrderException {
		// TODO Auto-generated method stub
		if(restaurantItemRepository.existsById(restId))
		{
			List<Orders> orders=orderRepository.getByRestaurant(restId);
			
			if(orders.isEmpty())
				throw new OrderException("Orders list is empty");
			
			return orders;
			
		}
		else
			throw new OrderException("Employee Not found");
	}

	@Override
	public List<Orders> topSellingOrders() throws OrderException {
		// TODO Auto-generated method stub
		
//		List<Orders> orders=orderRepository.getByDate(LocalDate.now());
//		
//		if(orders.isEmpty())
//			throw new OrderException("Orders not found today");
//		
		
		return null;
	}

}
