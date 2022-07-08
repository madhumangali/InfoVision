package com.infovision.canteen.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infovision.canteen.model.admin.MenuItem;
import com.infovision.canteen.model.order.OrderCartItem;

@Repository
public interface OrderCartItemRepository extends JpaRepository<OrderCartItem, UUID>{

}
