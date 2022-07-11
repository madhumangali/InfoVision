package com.infovision.canteen.model.delivery;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.infovision.canteen.model.Credentials.Credentials;

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
	
	
}
