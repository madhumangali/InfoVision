package com.infovision.canteen.service;

import java.util.UUID;

import com.infovision.canteen.dto.admin.AdminDto;
import com.infovision.canteen.exception.AdminException;
import com.infovision.canteen.model.admin.Admin;

public interface AdminService {

	AdminDto addAdmin(AdminDto adminDto);

	AdminDto editAdmin(AdminDto adminDto,UUID id);

	Admin viewAdmin(String email) throws AdminException;

	String deleteAdmin(String email) throws AdminException;

}
