package com.infovision.canteen.serviceimpl;

<<<<<<< HEAD
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
import com.infovision.canteen.dto.credentials.CredentialsDto;
import com.infovision.canteen.dto.employee.EmployeeDto;
import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.credentials.Credentials;
import com.infovision.canteen.model.delivery.Delivery;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.repository.AdminRepository;
import com.infovision.canteen.repository.CredentialsRepository;
import com.infovision.canteen.repository.DeliveryRepository;
=======
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.admin.AdminDto;
import com.infovision.canteen.dto.employee.EmployeeDto;
import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.repository.AdminRepository;
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
import com.infovision.canteen.repository.EmployeeRepository;
import com.infovision.canteen.service.AdminService;

@Service
<<<<<<< HEAD
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

=======
public class AdminserviceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public AdminDto addAdmin(AdminDto adminDto) {
		// TODO Auto-generated method stub
		Admin admin =new Admin();
		
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
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
<<<<<<< HEAD

		editAdmin(adminDto, admin.getId());

=======
		
		editAdmin(adminDto, admin.getId());
		
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
		return adminDto;
	}

	@Override
<<<<<<< HEAD
	public AdminDto editAdmin(AdminDto adminDto, UUID id) {
		// TODO Auto-generated method stub

		if (adminRepository.existsById(id)) {
			Admin admin = adminRepository.getOne(id);

=======
	public AdminDto editAdmin(AdminDto adminDto,UUID id) {
		// TODO Auto-generated method stub
		
		if(adminRepository.existsById(id))
		{
			Admin admin= adminRepository.getOne(id);
			
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
			admin.setCity(adminDto.getCity());
			admin.setCountry(adminDto.getCountry());
			admin.setEmail(adminDto.getEmail());
			admin.setFirstName(adminDto.getFirstName());
			admin.setGender(adminDto.getGender());
			admin.setLastName(adminDto.getLastName());
			admin.setMobileNumber(adminDto.getMobileNumber());
			admin.setState(adminDto.getState());
			admin.setImageUrl(adminDto.getImageUrl());
<<<<<<< HEAD

			adminRepository.save(admin);

		}

=======
			
			adminRepository.save(admin);
			
		}
		
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
		return adminDto;
	}

	@Override
	public List<Employee> getEmployeeList() throws Exception {
<<<<<<< HEAD
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
=======
		List<Employee> employee  = employeeRepository.findAll();
		
		if(employee.isEmpty()) {
			throw new Exception("employee list is empty");
		}
		
		return employee;
	}
	

}
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
