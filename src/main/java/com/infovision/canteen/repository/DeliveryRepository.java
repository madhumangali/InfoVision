package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.credentials.Credentials;
import com.infovision.canteen.model.delivery.Delivery;
@Repository
public interface DeliveryRepository  extends JpaRepository<Delivery, UUID>{

//	void save(Credentials credential);

	
	
	
=======
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.delivery.Delivery;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, UUID> {

	@Query("SELECT * FROM Delivery d WHERE d.credentials.userName =:userName AND d.credentials.password =:password")
	Delivery findUser(String userName, String password);

	Delivery getById(UUID deliveryId);
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4

}
