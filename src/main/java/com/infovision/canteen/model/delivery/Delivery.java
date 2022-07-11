package com.infovision.canteen.model.delivery;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
<<<<<<< HEAD
=======
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

<<<<<<< HEAD
import com.infovision.canteen.model.cart.Cart;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.employee.Profile;
=======
import com.infovision.canteen.model.Credentials.Credentials;
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID deliveryId;
	
<<<<<<< HEAD
	private String userName;
	
	private String password;
	
	private long mobileNumber;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
=======
//	private String userName;
//	
//	private String password;
	
	private long mobileNumber;
	
	@Enumerated(EnumType.STRING)
	private DeliveryPersonStatus deliveryPersonStatus;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Address address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Credentials credentials;
	
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
	
}
