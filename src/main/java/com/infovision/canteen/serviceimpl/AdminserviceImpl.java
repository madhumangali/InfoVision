package com.infovision.canteen.serviceimpl;

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
import com.infovision.canteen.repository.EmployeeRepository;
import com.infovision.canteen.service.AdminService;

@Service
public class AdminserviceImpl implements AdminService{

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
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
	public List<Employee> getEmployeeList() throws Exception {
		List<Employee> employee  = employeeRepository.findAll();
		
		if(employee.isEmpty()) {
			throw new Exception("employee list is empty");
		}
		
		return employee;
	}
	

}
