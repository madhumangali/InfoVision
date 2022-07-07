package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.credentials.Credentials;
@Repository
public interface CredentialsRepository  extends JpaRepository<Credentials, UUID>{

	Object findByuserName(String userName);



}
