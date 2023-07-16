package com.kidletgift.product.model.productDoc;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("store_product")
@Data
public class ProductDoc {

    @Id
    private String itemId;
    private String itemCategory;
    private String itemGender;

    @Indexed(unique = true)
    private String itemCode;

    @Indexed
    private String itemName;
    private Integer itemQuantity;
    private Double itemPrice;
    private List<ItemSubGroup> itemSubGroups;
    private List<String> itemFeatures;
    private AgeCategory ageCategory;
    private String stockId;
    private List<ItemImages> itemImages;
    private ItemDimension itemDimension;
    private List<TechnicalDetails> technicalDetails;
    private List<ItemDescriptionWithImages> itemDescriptionWithImages;
    private ItemStatus itemStatus;
    private Integer reorderLevel;

}
