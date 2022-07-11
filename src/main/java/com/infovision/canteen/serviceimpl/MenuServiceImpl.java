package com.infovision.canteen.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infovision.canteen.dto.admin.MenuItemDto;
import com.infovision.canteen.exception.MenuItemException;
import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.repository.AdminRepository;
import com.infovision.canteen.repository.MenuItemRepository;
import com.infovision.canteen.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuItemRepository menuItemRepositroy;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public List<MenuItemDto> addMenuItems(List<MenuItemDto> menuItemDtos,UUID adminId) throws MenuItemException {
		// TODO Auto-generated method stub
		Admin admin=adminRepository.getOne(adminId);
		
		if(adminRepository.existsById(adminId))
		{
			
		for(MenuItemDto menuItemDto : menuItemDtos)
		{
			MenuItem menuItem=new MenuItem();
			
			menuItem.setItemName(menuItemDto.getItemName());
			menuItem.setFoodType(menuItemDto.getFoodType());
			menuItem.setImageUrl(menuItemDto.getImageUrl());
			
			menuItem.setAdmin(admin);
			
			menuItemRepositroy.save(menuItem);
		}
		
		}
		else
			throw new MenuItemException("Admin details not found");
		
		return menuItemDtos;
	}

}
