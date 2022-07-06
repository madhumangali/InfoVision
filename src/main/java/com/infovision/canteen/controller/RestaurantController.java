package com.infovision.canteen.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infovision.canteen.dto.restaurant.RestaurantProfileDto;
import com.infovision.canteen.exception.ErrorHandler;
import com.infovision.canteen.service.RestaurantService;
//import com.infovision.canteen.serviceimpl.JwtUserDetailsService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;
	
//	@Autowired
//	private JwtUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/registerforrestaurant", method = RequestMethod.POST)
	public ResponseEntity<?> addRestaurant(@RequestBody RestaurantProfileDto restaurantProfileDto) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantService.addRestaurant(restaurantProfileDto), HttpStatus.OK);
//			userDetailsService.save(restaurantProfileDto.getEmail(),restaurantProfileDto.getPassword());
			log.info("new Restaurant is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new Restaurant is not added");
		}

		return response;
	
	}
	
	
	@RequestMapping(value = "/registerforrestaurant", method = RequestMethod.PUT)
	public ResponseEntity<?> editRestaurant(@RequestBody RestaurantProfileDto restaurantProfileDto,@RequestParam UUID id) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(restaurantService.editRestaurant(restaurantProfileDto,id), HttpStatus.OK);
			    log.info("admin details are edited");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("admin details are not edited");
		}

		return response;
		
	}
}
