package com.kidletgift.product.model.product;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ItemStatus {

    /**
     * OUT_OF_ORDER : when item has 0 quantity
     * NOT_AVAILABLE : when item is no longer available for sale. (Can have quantity)
     * ORDER_PENDING : order is pending and will available soon. This can grow in future as per demand.
     * AVAILABLE : When have stock
     */
    private String status;
    private Date statusUpdatedDate;
    private String statusUpdatedBy;

}
