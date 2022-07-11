package com.infovision.canteen.serviceimpl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.delivery.DeliveryProfileDto;
import com.infovision.canteen.model.delivery.Address;
import com.infovision.canteen.model.delivery.Delivery;
import com.infovision.canteen.model.delivery.DeliveryPersonStatus;
import com.infovision.canteen.repository.AddressRepository;
import com.infovision.canteen.repository.DeliveryRepository;
import com.infovision.canteen.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	
	//Login and set status as ACTIVE
	@Override
	public String login(String userName, String password) throws Exception {
		Delivery delivery = deliveryRepository.findUser(userName, password);
		if(delivery == null)
			throw new Exception("Invalid Username or Password");
		delivery.setDeliveryPersonStatus(DeliveryPersonStatus.ACTIVE);
		return "Successfully logged in and Delivery boy is now ACTIVE";
	}

	
	//Set Status as INACTIVE
	@Override
	public String updateDeliveryPersonStatus(UUID deliveryId) throws Exception {
		Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
		Delivery delivery = new Delivery();
		if(!optionalDelivery.isPresent())
			throw new Exception("Delivery Boy does not exist");
		delivery.setDeliveryPersonStatus(DeliveryPersonStatus.INACTIVE);
		return "Delivery Boy is now INACTIVE";
	}

	
	//Add Delivery Boy's Profile
	@Override
	public DeliveryProfileDto addDeliveryBoy(DeliveryProfileDto deliveryProfileDto) {
		Address address = new Address();
		Delivery delivery = new Delivery();
		
		address.setFirstName(deliveryProfileDto.getFirstName());
		address.setLastName(deliveryProfileDto.getLastName());
		address.setEmail(deliveryProfileDto.getEmail());
		address.setMobileNumber(deliveryProfileDto.getMobileNumber());
		address.setAddressLine(deliveryProfileDto.getAddressLine());
		address.setPincode(deliveryProfileDto.getPincode());
		address.setCountry(deliveryProfileDto.getCountry());
		address.setState(deliveryProfileDto.getState());
		address.setCity(deliveryProfileDto.getCity());
		address.setGender(deliveryProfileDto.getGender());
		address.setImageUrl(deliveryProfileDto.getImageUrl());
		addressRepository.save(address);
		
		delivery.setAddress(address);
		deliveryRepository.save(delivery);
		return deliveryProfileDto;
	}

	//Edit Delivery Boy's Profile by id
	@Override
	public DeliveryProfileDto editDeliveryBoy(DeliveryProfileDto deliveryProfileDto, UUID deliveryId) throws Exception {
		Optional<Delivery> optionalDelivery = deliveryRepository.findById(deliveryId);
		if(!optionalDelivery.isPresent())
			throw new Exception("Delivery Boy is Invalid");
		else {
			Delivery delivery = new Delivery();		
			delivery.getAddress().setFirstName(deliveryProfileDto.getFirstName());
			delivery.getAddress().setLastName(deliveryProfileDto.getLastName());
			delivery.getAddress().setEmail(deliveryProfileDto.getEmail());
			delivery.getAddress().setMobileNumber(deliveryProfileDto.getMobileNumber());
			delivery.getAddress().setAddressLine(deliveryProfileDto.getAddressLine());
			delivery.getAddress().setPincode(deliveryProfileDto.getPincode());
			delivery.getAddress().setCountry(deliveryProfileDto.getCountry());
			delivery.getAddress().setState(deliveryProfileDto.getState());
			delivery.getAddress().setCity(deliveryProfileDto.getCity());
			delivery.getAddress().setGender(deliveryProfileDto.getGender());
			delivery.getAddress().setImageUrl(deliveryProfileDto.getImageUrl());
			deliveryRepository.save(delivery);
		}
		return deliveryProfileDto;
	}

	
	//View Delivery Boy's Profile by id
	@Override
	public Optional<Delivery> viewDeliveryProfileById(UUID deliveryId) throws Exception {
		Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);
		if(delivery == null)
			throw new Exception("Delivery boy not found");
		return delivery;
	}

}
