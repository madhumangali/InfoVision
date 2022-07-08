package com.infovision.canteen.model.order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.infovision.canteen.model.cart.Cart;
import com.infovision.canteen.model.delivery.Delivery;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.payment.Payment;
import com.infovision.canteen.model.restaurant.RestaurantItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TopSellingOrders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID orderId;
	
	@OneToOne(cascade = CascadeType.ALL)
	private RestaurantItem restItem;
	
	private int count;
	
	private LocalDate date;

}
