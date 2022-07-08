package com.infovision.canteen.serviceimpl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.admin.AdminDto;
import com.infovision.canteen.exception.AdminException;
import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.repository.AdminRepository;
import com.infovision.canteen.service.AdminService;

@Service
public class AdminserviceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public AdminDto addAdmin(AdminDto adminDto) {
		// TODO Auto-generated method stub
		Admin admin =new Admin();
		
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
	public AdminDto editAdmin(AdminDto adminDto,UUID id) {
		// TODO Auto-generated method stub
		
		if(adminRepository.existsById(id))
		{
			Admin admin= adminRepository.getOne(id);
			
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
	

}
