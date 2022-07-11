package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

<<<<<<< HEAD
=======
import com.infovision.canteen.model.admin.Admin;
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
import com.infovision.canteen.model.employee.Employee;

@Repository

public interface EmployeeRepository extends JpaRepository<Employee, UUID>{

	@Query("select s from Employee s where s.profile.mobileNumber=:mobNo")
	Employee findByMobNo(long mobNo);

	@Query("select s from Employee s where s.profile.employeeId=:empId")
	Employee findByEmpId(long empId);
	
	@Query("select s from Employee s where s.profile.email=:email AND s.profile.password=:password")
	Employee findByMail(String email,String password);

}
