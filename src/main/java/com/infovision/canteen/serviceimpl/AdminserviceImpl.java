package com.infovision.canteen.serviceimpl;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Credential;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.admin.AdminDto;
<<<<<<< HEAD
import com.infovision.canteen.exception.AdminException;
=======
import com.infovision.canteen.dto.credentials.CredentialsDto;
import com.infovision.canteen.dto.employee.EmployeeDto;
>>>>>>> 9de4829f0dfbe6378cc2c3d4bc51037b69907b7a
import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.credentials.Credentials;
import com.infovision.canteen.model.delivery.Delivery;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.repository.AdminRepository;
import com.infovision.canteen.repository.CredentialsRepository;
import com.infovision.canteen.repository.DeliveryRepository;
import com.infovision.canteen.repository.EmployeeRepository;
import com.infovision.canteen.service.AdminService;

@Service
public class AdminserviceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CredentialsRepository credentialsRepository;

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Override
	public AdminDto addAdmin(AdminDto adminDto) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();

		admin.setCity(adminDto.getCity());
		admin.setCountry(adminDto.getCountry());
		admin.setEmail(adminDto.getEmail());
		admin.setFirstName(adminDto.getFirstName());
		admin.setGender(admin.getGender());
		admin.setLastName(admin.getLastName());
		admin.setMobileNumber(adminDto.getMobileNumber());
		admin.setPassword(adminDto.getPassword());
		admin.setImageUrl(adminDto.getImageUrl());
		adminRepository.save(admin);

		editAdmin(adminDto, admin.getId());

		return adminDto;
	}

	@Override
	public AdminDto editAdmin(AdminDto adminDto, UUID id) {
		// TODO Auto-generated method stub

		if (adminRepository.existsById(id)) {
			Admin admin = adminRepository.getOne(id);

			admin.setCity(adminDto.getCity());
			admin.setCountry(adminDto.getCountry());
			admin.setEmail(adminDto.getEmail());
			admin.setFirstName(adminDto.getFirstName());
			admin.setGender(adminDto.getGender());
			admin.setLastName(adminDto.getLastName());
			admin.setMobileNumber(adminDto.getMobileNumber());
			admin.setState(adminDto.getState());
			admin.setImageUrl(adminDto.getImageUrl());

			adminRepository.save(admin);

		}

		return adminDto;
	}
<<<<<<< HEAD

	@Override
	public Admin viewAdmin(String email) throws AdminException {
		// TODO Auto-generated method stub
		
		Admin admin=adminRepository.findByEmail(email);
		
		if(admin ==null)
			throw new AdminException("Admin Details not found");
		
		return admin;
	}

	@Override
	public String deleteAdmin(String email) throws AdminException {
		// TODO Auto-generated method stub
		
		Admin admin=adminRepository.findByEmail(email);
		
		if(admin ==null)
			throw new AdminException("Admin Details not found");
		
		adminRepository.delete(admin);
		
		return "Admin Details are deleted";
	}
	
=======
>>>>>>> 9de4829f0dfbe6378cc2c3d4bc51037b69907b7a

	@Override
	public List<Employee> getEmployeeList() throws Exception {
		List<Employee> employee = employeeRepository.findAll();

		if (employee.isEmpty()) {
			throw new Exception("employee list is empty");
		}

		return employee;
	}

	@Override
	public CredentialsDto addCredentials(CredentialsDto credentialsDto) throws Exception {

		if (Objects.nonNull(credentialsRepository.findByuserName(credentialsDto.getUserName())))
			throw new Exception("User already existed");

		// Hashing the password
		String encryptedpassword = credentialsDto.getPassword();
		try {
			encryptedpassword = hashPassword(credentialsDto.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Credentials credential = new Credentials();
		credential.setUserName(credentialsDto.getUserName());
		credential.setRole(credentialsDto.getRole());

//		credential.setPassword(encryptedpassword);
		credential.setPassword(credentialsDto.getPassword());

		credentialsRepository.save(credential);
//		deliveryRepository.save(credential);
		return credentialsDto;

	}

	private String hashPassword(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		byte[] digest = md.digest();
		String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
		return hash;
	}

	@Override
	public CredentialsDto editCredentials(CredentialsDto credentialsDto, UUID id) throws Exception {

		if (credentialsRepository.existsById(id)) {

			Credentials credentials = credentialsRepository.getOne(id);
			credentials.setUserName(credentialsDto.getUserName());
			credentials.setPassword(credentialsDto.getPassword());
			credentials.setRole(credentialsDto.getRole());
			credentialsRepository.save(credentials);
			return credentialsDto;
		}
		throw new Exception("credentials not found");
	}

	@Override
	public List<Credentials> getCredentialList() throws Exception {

		List<Credentials> credentials = credentialsRepository.findAll();

		if (credentials.isEmpty()) {
			throw new Exception("credentials list is empty");
		}

		return credentials;

	}

	@Override
	public String deleteCredentials(String userName) throws Exception {

//		
		Credentials credentials = credentialsRepository.findByuserName(userName);
		
			if(credentials.getUserName().equals(userName)) credentialsRepository.delete(credentials);
			else {
				throw new Exception("credentials not found");
			}
			return "deleted";
			
				
			
			
		
		
		
	
	
}}
