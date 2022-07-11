package com.infovision.canteen.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.infovision.canteen.dto.admin.AdminDto;
<<<<<<< HEAD
import com.infovision.canteen.dto.credentials.CredentialsDto;
=======
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
import com.infovision.canteen.exception.ErrorHandler;
import com.infovision.canteen.service.AdminService;
//import com.infovision.canteen.serviceimpl.JwtUserDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;

@RestController
@CrossOrigin("*")
@Log4j2
public class AdminController {

	@Autowired
	private AdminService adminService;
<<<<<<< HEAD

//	@Autowired
//	private JwtUserDetailsService userDetailsService;

	@RequestMapping(value = "/registerforadmin", method = RequestMethod.POST)
	public ResponseEntity<?> addAdmin(@RequestBody AdminDto adminDto) throws Exception {

=======
	
//	@Autowired
//	private JwtUserDetailsService userDetailsService;
	
	@RequestMapping(value = "/registerforadmin", method = RequestMethod.POST)
	public ResponseEntity<?> addAdmin(@RequestBody AdminDto adminDto) throws Exception {
		
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(adminService.addAdmin(adminDto), HttpStatus.OK);
//			userDetailsService.save(adminDto.getEmail(),adminDto.getPassword());
<<<<<<< HEAD

			log.info("new admin is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			log.error("new admin is not added");
		}

		return response;

	}

	@RequestMapping(value = "/registerforadmin", method = RequestMethod.PUT)
	public ResponseEntity<?> editAdmin(@RequestBody AdminDto adminDto, @RequestParam UUID id) throws Exception {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(adminService.editAdmin(adminDto, id), HttpStatus.OK);
			log.info("admin details are edited");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			log.error("admin details are not edited");
		}

		return response;

	}

	@GetMapping("/view users")
	@Operation(summary = "view employees")
	public ResponseEntity<?> getEmployeeList() {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(adminService.getEmployeeList(), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}

	// adding credentials

	@RequestMapping(value = "/registerforcredentials", method = RequestMethod.POST)
	public ResponseEntity<?> addcredentials(@RequestBody CredentialsDto credentialsDto) throws Exception {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(adminService.addCredentials(credentialsDto), HttpStatus.OK);

			log.info("new credential is added");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			log.error("new credential is not added");
		}
		return response;

	}
	
	
	
	@RequestMapping(value = "/registerforcredentials", method = RequestMethod.PUT)
	public ResponseEntity<?> editcredentials(@RequestBody CredentialsDto credentialsDto,@RequestParam UUID id) throws Exception {
=======
			
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
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
		
		ResponseEntity<?> response;

		try {
<<<<<<< HEAD
			response = new ResponseEntity<>(adminService.editCredentials(credentialsDto,id), HttpStatus.OK);
//			userDetailsService.save(adminDto.getEmail(),adminDto.getPassword());
			
			    log.info(" credential is edited");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("credential is not edited");
		}
		return response;
	}
	
	@GetMapping("/view credential")
	@Operation(summary = "view credential list")
	public ResponseEntity<?> getCredentialList() {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(adminService.getCredentialList(), HttpStatus.OK);
		} catch (Exception e) {
=======
			response = new ResponseEntity<>(adminService.editAdmin(adminDto,id), HttpStatus.OK);
			    log.info("admin details are edited");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("admin details are not edited");
		}

		return response;
		
	}
	
	@GetMapping("/view users")
	@Operation(summary = "view employees")
	public ResponseEntity<?> getEmployeeList( )    {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<>(adminService.getEmployeeList(), HttpStatus.OK);
		}
		catch(Exception e)
		{
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}

		return response;
	}
<<<<<<< HEAD
	
	
	
	@RequestMapping(value = "/registerforcredentials", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCredentials(@RequestParam String userName) throws Exception {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(adminService.deleteCredentials(userName), HttpStatus.OK);

			log.info("credential is deleted");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			log.error("new credential is not deleted");
		}
		return response;
	
	}
=======
>>>>>>> 739b134e24a44c41db7a008bc59274776b8936d4
}
