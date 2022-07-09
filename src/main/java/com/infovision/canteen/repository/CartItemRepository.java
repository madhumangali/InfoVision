package com.infovision.canteen.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.Admin;
import com.infovision.canteen.model.cart.CartItem;

@Repository
@Transactional
public interface CartItemRepository extends JpaRepository<CartItem, UUID>{

	@Query("select s from CartItem s where s.cart.cartId=:cartId")
	List<CartItem> findCartItems(UUID cartId);

	@Query("select s from CartItem s where s.cart.cartId=:cartId AND s.restaurantItem.itemId=:itemId")
	CartItem findItems(UUID cartId, UUID itemId);

	 @Modifying
	@Query("DELETE FROM CartItem s WHERE s.cartItemId=:cartItemId")
	void delete(UUID cartItemId);

	 @Query("select s from CartItem s where s.cart.cartId=:cartId")
	List<CartItem> findByCartId(UUID cartId);

	
}
