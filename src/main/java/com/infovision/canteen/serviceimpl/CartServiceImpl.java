package com.infovision.canteen.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.cart.CartItemDto;
import com.infovision.canteen.dto.cart.ViewItemDto;
import com.infovision.canteen.exception.CartException;
import com.infovision.canteen.model.cart.CartItem;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.order.OrderCartItem;
import com.infovision.canteen.model.restaurant.RestaurantItem;
import com.infovision.canteen.repository.CartItemRepository;
import com.infovision.canteen.repository.CartRepository;
import com.infovision.canteen.repository.EmployeeRepository;
import com.infovision.canteen.repository.OrderCartItemRepository;
import com.infovision.canteen.repository.RestaurantItemRepository;
import com.infovision.canteen.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private RestaurantItemRepository restaurantItemRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CartRepository cartRepository;

	@Override
	public String addToCart(UUID itemId, UUID empId, int quantity) throws CartException {
		// TODO Auto-generated method stub

		if (restaurantItemRepository.existsById(itemId)) {
			if (employeeRepository.existsById(empId)) {

				RestaurantItem restaurantItem = restaurantItemRepository.getOne(itemId);
				Employee employee = employeeRepository.getOne(empId);

				List<CartItem> cartItems = cartItemRepository.findByCartId(employee.getCart().getCartId());

				if (cartItems.isEmpty()) {
					
					addProductTocart(employee,restaurantItem,quantity);
					
				} else {
					
					int itemCount = 0, itemSize = cartItems.size();
					
					for (CartItem item : cartItems) 
					{
						
						if (item.getRestaurantItem().getRestaurant().getRestaurantid()
								.equals(restaurantItem.getRestaurant().getRestaurantid()))
							itemCount++;

					}
					
					if(itemCount==itemSize)
					{
						
						addProductTocart(employee,restaurantItem,quantity);
					}

				}

			} else
				throw new CartException("employee not found");
		} else
			throw new CartException("Item not found");

		return "Item is added to cart";
	}

	public void addProductTocart(Employee employee,RestaurantItem restaurantItem,int quantity)
	{
		CartItem cartItem = new CartItem();

		cartItem.setCart(employee.getCart());
		cartItem.setRestaurantItem(restaurantItem);
		double discount=restaurantItem.getDiscount()*100;
        cartItem.setAmount(quantity * (restaurantItem.getItemprice()-discount));
		cartItem.setQuantity(quantity);
		cartItemRepository.save(cartItem);
	}
	
	@Override
	public List<CartItem> viewCartItems(UUID cartId) throws Exception {
		// TODO Auto-generated method stub

		CartItemDto cartItemDto = new CartItemDto();

		List<CartItem> cartItems = cartItemRepository.findCartItems(cartId);

		return cartItems;

	}

	@Override
	public CartItemDto editCartItems(UUID itemId, UUID cartId, int quantity) throws Exception {
		// TODO Auto-generated method stub
		CartItemDto cartItemDto = new CartItemDto();

		List<ViewItemDto> RestaurantItemDtos = new ArrayList();

		double total = 0;

		List<CartItem> cartItems = cartItemRepository.findCartItems(cartId);

		if (cartItems.isEmpty())
			throw new Exception("Cart Id not found");

		for (CartItem cartItem : cartItems) {

			if (cartItem.getCart().getCartId().equals(cartId)
					&& cartItem.getRestaurantItem().getItemId().equals(itemId)) {
				cartItem.setQuantity(quantity);
				cartItem.setAmount(quantity * cartItem.getRestaurantItem().getItemprice());
				cartItemRepository.save(cartItem);
			}

			ViewItemDto restaurantItemDto = new ViewItemDto();

			restaurantItemDto.setImageUrl(cartItem.getRestaurantItem().getImageUrl());
			restaurantItemDto.setItemDesc(cartItem.getRestaurantItem().getItemDesc());
			restaurantItemDto.setAmount(cartItem.getAmount());
			restaurantItemDto.setItemName(cartItem.getRestaurantItem().getItemName());
			restaurantItemDto.setRating(cartItem.getRestaurantItem().getRating());
			restaurantItemDto.setRestaurant(cartItem.getRestaurantItem().getRestaurant());
			restaurantItemDto.setStatus(cartItem.getRestaurantItem().getStatus());

			RestaurantItemDtos.add(restaurantItemDto);

			total += restaurantItemDto.getAmount();

		}

		cartItemDto.setRestaurantItems(RestaurantItemDtos);
		cartItemDto.setTotal(total);

		return cartItemDto;
	}

	@Override
	@Transactional
	public String deleteCartItems(UUID itemId, UUID cartId) throws Exception {
		// TODO Auto-generated method stub
		List<CartItem> cartItems = cartItemRepository.findCartItems(cartId);

		if (cartItems.isEmpty())
			throw new Exception("Cart Id not found");

		List<CartItem> cartItems1 = new ArrayList<>();

		for (CartItem cartItem : cartItems) {

			if (cartItem.getCart().getCartId().equals(cartId)
					&& cartItem.getRestaurantItem().getItemId().equals(itemId)) {

				cartItemRepository.delete(cartItem.getCartItemId());

			}

		}
		return "CartItem deleted from Cart";
	}

}
