package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.credentials.Credentials;
import com.infovision.canteen.model.delivery.Delivery;
@Repository
public interface DeliveryRepository  extends JpaRepository<Delivery, UUID>{

//	void save(Credentials credential);

	
	
	

}
