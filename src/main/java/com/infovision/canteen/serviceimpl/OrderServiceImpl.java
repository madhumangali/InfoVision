package com.infovision.canteen.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.controller.PaymentController;
import com.infovision.canteen.dto.Order.OrderDto;
import com.infovision.canteen.exception.OrderException;
import com.infovision.canteen.model.cart.CartItem;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.order.EmployeeOrderStatus;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.order.OrderStatus;
import com.infovision.canteen.model.order.Orders;
import com.infovision.canteen.model.order.TopSellingOrders;
import com.infovision.canteen.model.payment.Mode;
import com.infovision.canteen.model.payment.Payment;
import com.infovision.canteen.model.restaurant.ItemStatus;
import com.infovision.canteen.model.restaurant.Status;
import com.infovision.canteen.repository.CartItemRepository;
import com.infovision.canteen.repository.CartRepository;
import com.infovision.canteen.repository.EmployeeRepository;
import com.infovision.canteen.repository.OrderCartItemRepository;
import com.infovision.canteen.repository.OrderRepository;
import com.infovision.canteen.repository.PaymentRepository;
import com.infovision.canteen.repository.RestaurantItemRepository;
import com.infovision.canteen.repository.RestaurantRepository;
import com.infovision.canteen.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private RestaurantItemRepository restaurantItemRepository;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderCartItemRepository orderCartItemRepository;

	@Autowired
	private PaymentController paymentController;

	@Override
	public String orderItem(OrderDto orderDto) throws Exception {
		// TODO Auto-generated method stub

		Orders order = new Orders();

		if (employeeRepository.existsById(orderDto.getEmpId()))
			order.setEmployee(employeeRepository.getOne(orderDto.getEmpId()));
		else
			throw new OrderException("Employee Not found");

		if (cartRepository.existsById(orderDto.getCartId())) {

			if (employeeRepository.getOne(orderDto.getEmpId()).getCart().getCartId().equals(orderDto.getCartId())) {

				List<CartItem> cartItems = cartItemRepository.findCartItems(orderDto.getCartId());

				for (CartItem cartItem : cartItems) {

					OrderCartItem orderCartItem = new OrderCartItem();

					orderCartItem.setCart(cartItem.getCart());
					orderCartItem.setRestaurantItem(cartItem.getRestaurantItem());
					orderCartItem.setAmount(cartItem.getAmount());
					orderCartItem.setQuantity(cartItem.getQuantity());
					orderCartItem.setOrder(order);

					orderCartItemRepository.save(orderCartItem);

				}

			} else
				throw new OrderException("Cart not belongs current Employee");

		} else
			throw new OrderException("Cart Not found");

		Payment payment = new Payment();

		payment.setAmount(cartRepository.getOne(orderDto.getCartId()).getTotalamount());
		payment.setMode(Mode.PAYTM);

		String s = String.valueOf(cartRepository.getOne(orderDto.getCartId()).getTotalamount());

		paymentController.getRedirect(orderDto.getEmpId().toString(), s, order.getOrderId().toString());

		paymentRepository.save(payment);

		order.setDate(LocalDate.now());
		order.setTime(LocalTime.now());
		order.setPayment(payment);

		order.setEmployeeOrderStatus(EmployeeOrderStatus.CONFIRM);

		orderRepository.save(order);

		List<OrderCartItem> orderCartItems = orderCartItemRepository.findByOrderId(order.getOrderId());

		for (OrderCartItem orderCartItem : orderCartItems) {

			if (orderCartItem.getRestaurantItem().getStatus().equals(ItemStatus.AVAILABLE) && 
					orderCartItem.getRestaurantItem().getRestaurant().getRestaurantStatus().equals(Status.ACTIVE)) {
				orderCartItem.setRestaurantOrderStatus(OrderStatus.ACCEPT);
				orderCartItemRepository.save(orderCartItem);
			}

		}

		return "Order Placed Successfully";
	}

	@Override
	public String cancelOrderItem(UUID orderId) throws OrderException {
		// TODO Auto-generated method stub

		if (orderRepository.existsById(orderId)) {
			Orders order = orderRepository.getOne(orderId);

			if (LocalTime.now().compareTo(order.getTime()) <= 5)
				order.setEmployeeOrderStatus(EmployeeOrderStatus.CANCEL);
			else
				throw new OrderException("Time Is  Expired to cancel the order");

			orderRepository.save(order);
		} else
			throw new OrderException("Order Not found");

		return "Order Cancelled";
	}

	@Override
	public List<OrderCartItem> getEmpOrders(UUID empId) throws OrderException {
		// TODO Auto-generated method stub

		if (employeeRepository.existsById(empId)) {
			Employee emp = employeeRepository.getOne(empId);

			List<OrderCartItem> orders = orderCartItemRepository.getByCart(emp.getCart().getCartId());

			if (orders.isEmpty())
				throw new OrderException("Orders list is empty");

			return orders;

		} else
			throw new OrderException("Employee Not found");

	}

	@Override
	public List<OrderCartItem> getAllOrders() throws OrderException {
		// TODO Auto-generated method stub

		List<OrderCartItem> orders = orderCartItemRepository.getAll(LocalDate.now());

		if (orders.isEmpty())
			throw new OrderException("Orders list is empty");

		return orders;

	}

	@Override
	public List<OrderCartItem> getRestaurantOrders(UUID restId) throws OrderException {
		// TODO Auto-generated method stub
		if (restaurantRepository.existsById(restId)) {
			List<OrderCartItem> orders = orderCartItemRepository.getByRestaurant(restId, LocalDate.now());

			if (orders.isEmpty())
				throw new OrderException("Orders list is empty");

			return orders;

		} else
			throw new OrderException("Restaurant Not found");
	}

	@Override
	public List<TopSellingOrders> topSellingOrders() throws OrderException {
		// TODO Auto-generated method stub

		List<TopSellingOrders> orders = orderCartItemRepository.getTopOrders(LocalDate.now());

		if (orders.isEmpty())
			throw new OrderException("Orders not found today");

		return orders;
	}

}
