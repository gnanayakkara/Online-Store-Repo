package com.kidletgift.product.service.product.serviceinterface;

import com.kidletgift.product.dto.product.ProductDTO;
import com.kidletgift.product.exception.GiftItemException;

import java.util.List;

public interface ProductService {

    /**
     * Save item to Product
     * @param productDTO
     * @return
     * @throws Exception
     */
    public Boolean saveProductItem(ProductDTO productDTO) throws GiftItemException;

    /**
     * Get the item by name. This works as a like query. Using for search item by name
     * @param itemName
     * @return
     * @throws Exception
     */
    public List<ProductDTO> findItemByRegexName(String itemName) throws Exception;

    /**
     * Generic update method to update Gift Item
     * @param productDTO
     * @return
     * @throws Exception
     */
    public ProductDTO updateGiftItem(ProductDTO productDTO) throws GiftItemException;

    /**
     * Get the item using Item id
     * @param itemId
     * @return
     * @throws GiftItemException
     */
    public ProductDTO findItemByItemId(String itemId) throws GiftItemException;
}
