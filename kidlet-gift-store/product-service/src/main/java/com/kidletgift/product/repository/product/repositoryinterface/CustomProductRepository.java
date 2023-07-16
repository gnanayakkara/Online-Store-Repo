package com.kidletgift.product.repository.product.repositoryinterface;

import com.kidletgift.product.exception.GiftItemSaveOrUpdateException;
import com.kidletgift.product.model.productDoc.ProductDoc;

import java.util.List;

public interface CustomProductRepository {

    /**
     * Find items by name.
     * @param itemName
     * @return
     */
    public List<ProductDoc> findItemByRegexpName(String itemName) throws Exception;

    /**
     * Generic update method to update Gift Item
     * @param productDoc
     * @return
     * @throws Exception
     */
    public ProductDoc updateGiftItem(ProductDoc productDoc) throws GiftItemSaveOrUpdateException;

}
