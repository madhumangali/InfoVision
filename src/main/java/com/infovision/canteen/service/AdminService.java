package com.infovision.canteen.service;

import java.util.List;
import java.util.UUID;

import com.infovision.canteen.dto.admin.AdminDto;
import com.infovision.canteen.dto.employee.EmployeeDto;
import com.infovision.canteen.model.employee.Employee;

public interface AdminService {

	AdminDto addAdmin(AdminDto adminDto);

	AdminDto editAdmin(AdminDto adminDto,UUID id);


		List<Employee> getEmployeeList() throws Exception;

}
