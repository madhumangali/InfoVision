package com.infovision.canteen.model.order;

import java.util.UUID;

//@Data
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
public class TopSellingOrders {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	private UUID orderId;
	
	
	private UUID itemId;
	
	private long count;

	public TopSellingOrders(UUID restItem, long count) {
		super();
		this.itemId = restItem;
		this.count = count;
	}
	
	

}
