package com.infovision.canteen.service;

import java.util.List;
import java.util.UUID;

import com.infovision.canteen.dto.admin.MenuItemDto;
import com.infovision.canteen.exception.MenuItemException;

public interface MenuService {

	List<MenuItemDto> addMenuItems(List<MenuItemDto> menuItemDtos,UUID adminId) throws MenuItemException;

}
