package com.infovision.canteen.service;

import java.util.List;
import java.util.UUID;

import com.infovision.canteen.dto.admin.AdminDto;
<<<<<<< HEAD
import com.infovision.canteen.dto.credentials.CredentialsDto;
import com.infovision.canteen.model.credentials.Credentials;
=======
import com.infovision.canteen.dto.employee.EmployeeDto;
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
import com.infovision.canteen.model.employee.Employee;

public interface AdminService {

	AdminDto addAdmin(AdminDto adminDto);

<<<<<<< HEAD
	AdminDto editAdmin(AdminDto adminDto, UUID id);

	List<Employee> getEmployeeList() throws Exception;

	CredentialsDto addCredentials(CredentialsDto credentialsDto) throws Exception;

	CredentialsDto editCredentials(CredentialsDto credentialsDto, UUID id) throws Exception;

	List<Credentials> getCredentialList() throws Exception;

	String deleteCredentials(String userName) throws Exception;
=======
	AdminDto editAdmin(AdminDto adminDto,UUID id);


		List<Employee> getEmployeeList() throws Exception;
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4

}
