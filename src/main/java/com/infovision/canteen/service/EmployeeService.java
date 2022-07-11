package com.infovision.canteen.service;

import java.util.List;
import java.util.UUID;

import com.infovision.canteen.dto.employee.ProfileDto;
import com.infovision.canteen.exception.MenuItemException;
import com.infovision.canteen.model.admin.FoodType;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.restaurant.RestaurantItem;

public interface EmployeeService {

	ProfileDto addEmployee(ProfileDto profileDto) throws Exception;

	ProfileDto editEmployee(ProfileDto profileDto, UUID id) throws Exception;

	List<RestaurantItem> homePage(FoodType foodType, String location);

	List<RestaurantItem> homePageAll() throws MenuItemException;

	Employee viewProfile(long empId) throws Exception;

	String login(String email, String password) throws Exception;

}
