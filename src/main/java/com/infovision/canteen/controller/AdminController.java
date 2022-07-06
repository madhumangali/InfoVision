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

import com.infovision.canteen.dto.admin.AdminDto;
import com.infovision.canteen.exception.ErrorHandler;
import com.infovision.canteen.service.AdminService;
//import com.infovision.canteen.serviceimpl.JwtUserDetailsService;

import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class AdminController {

	@Autowired
	private AdminService adminService;
	
//	@Autowired
//	private JwtUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/registerforadmin", method = RequestMethod.POST)
	public ResponseEntity<?> addAdmin(@RequestBody AdminDto adminDto) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(adminService.addAdmin(adminDto), HttpStatus.OK);
//			userDetailsService.save(adminDto.getEmail(),adminDto.getPassword());
			
			    log.info("new admin is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("new admin is not added");
		}

		return response;
		
	}
	
	@RequestMapping(value = "/registerforadmin", method = RequestMethod.PUT)
	public ResponseEntity<?> editAdmin(@RequestBody AdminDto adminDto,@RequestParam UUID id) throws Exception {
		
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(adminService.editAdmin(adminDto,id), HttpStatus.OK);
			    log.info("admin details are edited");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("admin details are not edited");
		}

		return response;
		
	}
}
