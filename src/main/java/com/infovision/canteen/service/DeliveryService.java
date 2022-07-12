package com.infovision.canteen.service;

import java.util.Optional;
import java.util.UUID;

import com.infovision.canteen.dto.delivery.DeliveryProfileDto;
import com.infovision.canteen.model.delivery.Delivery;
import com.infovision.canteen.model.delivery.DeliveryPersonStatus;
import com.infovision.canteen.model.order.Orders;

public interface DeliveryService {

	String login(String userName, String password) throws Exception;

	String updateDeliveryPersonStatus(UUID deliveryId,DeliveryPersonStatus status) throws Exception;

	Delivery addDeliveryBoy(DeliveryProfileDto deliveryProfileDto,UUID deliveryId) throws Exception;

	Delivery editDeliveryBoy(DeliveryProfileDto deliveryProfileDto, UUID deliveryId) throws Exception;

	Optional<Delivery> viewDeliveryProfileById(UUID deliveryId) throws Exception;

	Orders getDeliveryOrder(UUID deliveryId) throws Exception;

}
