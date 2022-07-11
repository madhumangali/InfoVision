package com.infovision.canteen.model.employee;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID profileId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private long mobileNumber;
	
	private String country;
	
	private String state;
	
	private long pincode;
	
	private String city;
	
	private String Gender;
	
	private String password;
	
	private long employeeId;
	
	private String imageUrl;
	
}
