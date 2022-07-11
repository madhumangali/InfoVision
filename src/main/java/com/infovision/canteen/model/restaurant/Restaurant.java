package com.infovision.canteen.model.restaurant;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

<<<<<<< HEAD
=======
import com.infovision.canteen.model.order.OrderStatus;

>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID restaurantid;
	
	@OneToOne(cascade = CascadeType.ALL)
	private RestaurantProfile restaurantProfile;
	
	@Enumerated(EnumType.STRING)
	private Status restaurantStatus;
	
<<<<<<< HEAD
	
=======
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
}
