package com.infovision.canteen.model.Credentials;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.infovision.canteen.model.cart.Cart;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.employee.Profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID credentialId;
	
	private String userName;
	
	private String password;
	
	@Enumerated(EnumType.STRING)
	private Role role;
}
