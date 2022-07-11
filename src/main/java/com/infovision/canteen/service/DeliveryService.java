package com.infovision.canteen.service;

import java.util.Optional;
import java.util.UUID;

import com.infovision.canteen.dto.delivery.DeliveryProfileDto;
import com.infovision.canteen.model.delivery.Delivery;

public interface DeliveryService {

	String login(String userName, String password) throws Exception;

	String updateDeliveryPersonStatus(UUID deliveryId) throws Exception;

	DeliveryProfileDto addDeliveryBoy(DeliveryProfileDto deliveryProfileDto);

	DeliveryProfileDto editDeliveryBoy(DeliveryProfileDto deliveryProfileDto, UUID deliveryId) throws Exception;

	Optional<Delivery> viewDeliveryProfileById(UUID deliveryId) throws Exception;

}
