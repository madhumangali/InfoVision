package com.infovision.canteen.service;

import java.util.UUID;

import com.infovision.canteen.dto.admin.AdminDto;

public interface AdminService {

	AdminDto addAdmin(AdminDto adminDto);

	AdminDto editAdmin(AdminDto adminDto,UUID id);

}
