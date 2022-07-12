package com.infovision.canteen.serviceimpl;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.delivery.DeliveryProfileDto;
import com.infovision.canteen.model.delivery.Address;
import com.infovision.canteen.model.delivery.Delivery;
import com.infovision.canteen.model.delivery.DeliveryPersonStatus;
import com.infovision.canteen.model.order.Orders;
import com.infovision.canteen.repository.AddressRepository;
import com.infovision.canteen.repository.DeliveryRepository;
import com.infovision.canteen.repository.OrderRepository;
import com.infovision.canteen.service.DeliveryService;

@Service
public class DeliveryServiceImpl implements DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private OrderRepository orderRepository;

	// Login and set status as ACTIVE
	@Override
	public String login(String userName, String password) throws Exception {
		Delivery delivery = deliveryRepository.findUser(userName, password);
		if (delivery == null)
			throw new Exception("Invalid Username or Password");
		delivery.setDeliveryPersonStatus(DeliveryPersonStatus.ACTIVE);
		return "Successfully logged in and Delivery boy is now ACTIVE";
	}

	// Set Status as INACTIVE and ACTIVE
	@Override
	public String updateDeliveryPersonStatus(UUID deliveryId, DeliveryPersonStatus status) throws Exception {

		Delivery optionalDelivery = deliveryRepository.getOne(deliveryId);

		if (optionalDelivery == null)
			throw new Exception("Delivery Boy does not exist");
		optionalDelivery.setDeliveryPersonStatus(status);
		deliveryRepository.save(optionalDelivery);

		return "Delivery Boy is now INACTIVE";
	}

	// Add Delivery Boy's Profile
	@Override
	public Delivery addDeliveryBoy(DeliveryProfileDto deliveryProfileDto, UUID deliveryId) throws Exception {

		Delivery delivery = deliveryRepository.getOne(deliveryId);

		if (deliveryRepository.existsById(deliveryId)) {

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

			addressRepository.save(delivery.getAddress());

			deliveryRepository.save(delivery);
		} else
			throw new Exception("Delivery Boy Details not found");

		return delivery;
	}

	// Edit Delivery Boy's Profile by id
	@Override
	public Delivery editDeliveryBoy(DeliveryProfileDto deliveryProfileDto, UUID deliveryId) throws Exception {

		Delivery delivery = deliveryRepository.getOne(deliveryId);

		if (deliveryRepository.existsById(deliveryId)) {

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

			addressRepository.save(delivery.getAddress());

			deliveryRepository.save(delivery);
		} else
			throw new Exception("Delivery Boy Details not found");

		return delivery;

	}

	// View Delivery Boy's Profile by id
	@Override
	public Optional<Delivery> viewDeliveryProfileById(UUID deliveryId) throws Exception {
		Optional<Delivery> delivery = deliveryRepository.findById(deliveryId);
		if (delivery == null)
			throw new Exception("Delivery boy not found");
		return delivery;
	}

	@Override
	public Orders getDeliveryOrder(UUID deliveryId) throws Exception {
		// TODO Auto-generated method stub
		Orders order = orderRepository.getOrderById(deliveryId);

		if (order == null)
			throw new Exception("Orders are not assigned");

		return order;
	}

}
