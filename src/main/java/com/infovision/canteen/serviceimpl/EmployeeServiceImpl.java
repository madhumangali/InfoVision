package com.infovision.canteen.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.employee.ProfileDto;
import com.infovision.canteen.exception.MenuItemException;
import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.admin.FoodType;
import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.model.cart.Cart;
import com.infovision.canteen.model.employee.Employee;
import com.infovision.canteen.model.employee.Profile;
import com.infovision.canteen.model.restaurant.Restaurant;
import com.infovision.canteen.model.restaurant.RestaurantItem;
import com.infovision.canteen.repository.AdminRepository;
import com.infovision.canteen.repository.CartRepository;
import com.infovision.canteen.repository.EmployeeRepository;
import com.infovision.canteen.repository.MenuItemRepository;
import com.infovision.canteen.repository.ProfileRepository;
import com.infovision.canteen.repository.RestaurantItemRepository;
import com.infovision.canteen.repository.RestaurantRepository;
import com.infovision.canteen.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private MenuItemRepository menuItemRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private RestaurantItemRepository restaurantItemRepository;
	
	@Override
	public ProfileDto addEmployee(ProfileDto profileDto) throws Exception {
		// TODO Auto-generated method stub
		
		Employee employee=new Employee();
		
		Profile profile =new Profile();
		
		profile.setCity(profileDto.getCity());
		profile.setCountry(profileDto.getCountry());
		profile.setEmail(profileDto.getEmail() );
		profile.setFirstName(profileDto.getFirstName());
		profile.setGender(profileDto.getGender());
		profile.setLastName(profileDto.getLastName());
		profile.setMobileNumber(profileDto.getMobileNumber());
		profile.setPassword(profileDto.getPassword());
		profile.setState(profileDto.getCity());
		profile.setImageUrl(profileDto.getImageUrl());
		
		employee.setProfile(profile);
		
		Cart cart=new Cart();
		
		employee.setCart(cart);
		
		profileRepository.save(profile);
		
		cartRepository.save(cart);
		
		employeeRepository.save(employee);
		
		editEmployee(profileDto,employee.getEmpId());
		
		return profileDto;
	}

	@Override
	public ProfileDto editEmployee(ProfileDto profileDto, UUID id) throws Exception {
		// TODO Auto-generated method stub
		
		if(employeeRepository.existsById(id))
		{
			Employee employee= employeeRepository.getOne(id);
			
			employee.getProfile().setCity(profileDto.getCity());
			employee.getProfile().setCountry(profileDto.getCountry());	
			employee.getProfile().setEmail(profileDto.getEmail());
			employee.getProfile().setFirstName(profileDto.getFirstName());
			employee.getProfile().setLastName(profileDto.getLastName());
			employee.getProfile().setPassword(profileDto.getPassword());
			employee.getProfile().setGender(profileDto.getGender());
			employee.getProfile().setMobileNumber(profileDto.getMobileNumber());
			employee.getProfile().setEmployeeId(profileDto.getEmployeeId());
			employee.getProfile().setState(profileDto.getState());
			employee.getProfile().setImageUrl(profileDto.getImageUrl());
			
			employeeRepository.save(employee);
			
		}
		
		else
			throw new Exception ("Id not found");
		
		return profileDto;
	}

	@Override
	public List<RestaurantItem> homePage(FoodType foodType, String location) {
		// TODO Auto-generated method stub
		Admin admin=adminRepository.getByLocation(location);
		
		List<Restaurant> restaurants=restaurantRepository.findByLocation(location);
		
		List<MenuItem> menuItems=menuItemRepository.getByAdmin(admin.getId());
		
		List<RestaurantItem> restauarantItems=new ArrayList<>();
		
		for(Restaurant restaurant: restaurants)
		{
			
			List<RestaurantItem> restaurantItems1=restaurantItemRepository.findByRestaurant(restaurant.getRestaurantid());
			
			
			for(RestaurantItem restaurantItem: restaurantItems1)
			{
				for(MenuItem menuItem:menuItems)
				{
					
				if(menuItem.getItemName().equals(restaurantItem.getItemName()) && menuItem.getFoodType().equals(foodType))
				{
					restauarantItems.add(restaurantItem);
					break;
				}
				
				}
					
			}
			
		}
		
		
		return restauarantItems;
	}

	@Override
	public List<RestaurantItem> homePageAll() throws MenuItemException {
		// TODO Auto-generated method stub
		
		List<RestaurantItem> restaurantItems=restaurantItemRepository.findAll();
		
		if(restaurantItems.isEmpty())
		{
			throw new MenuItemException("Restaurant Items are empty");
		}
		
		return restaurantItems;
	}

	@Override
	public Employee viewProfile(long empId) throws Exception {
		// TODO Auto-generated method stub
		
		Employee employee=employeeRepository.findByEmpId(empId);
		
		if(employee==null)
			throw new Exception("Employee ProfileNot found");
		
		return employee;
	}

	@Override
	public String login(String email, String password) throws Exception {
		// TODO Auto-generated method stub
		
		Employee employee=employeeRepository.findByMail(email,password);
		
		if(employee == null)
				throw new Exception("Credentails are Invalid");
		
		return "Credentails are valid";
	}

}
