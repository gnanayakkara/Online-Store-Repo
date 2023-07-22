package com.kidletgift.order.model.orderdoc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("store_order")
@Data
public class OrderDoc {

    @Id
    private String storeOrdersId;
    @Indexed(unique = true)
    private String userId;
    private List<CartItem> cartItems;
    private ArrayList<PurchaseHistory> purchaseHistory;
}
