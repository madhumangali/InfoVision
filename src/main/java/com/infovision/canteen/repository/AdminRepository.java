package com.infovision.canteen.repository;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.restaurant.RestaurantProfile;

@Repository
@Transactional
public interface AdminRepository extends JpaRepository<Admin, UUID>{

	@Query("select s from Admin s where s.city=:location")
	Admin getByLocation(String location);

}
