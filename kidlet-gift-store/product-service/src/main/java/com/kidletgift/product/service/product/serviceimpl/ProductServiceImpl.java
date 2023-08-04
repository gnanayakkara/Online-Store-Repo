package com.kidletgift.product.service.product.serviceimpl;

import com.kidletgift.product.dto.product.ProductDTO;
import com.kidletgift.product.exception.GiftItemException;
import com.kidletgift.product.exception.GiftItemNotFoundException;
import com.kidletgift.product.mapper.product.ProductMapper;
import com.kidletgift.product.model.product.ProductDoc;
import com.kidletgift.product.repository.product.ProductRepository;
import com.kidletgift.product.repository.product.repositoryinterface.CustomProductRepository;
import com.kidletgift.product.service.product.serviceinterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    private CustomProductRepository customProductRepository;

    private ProductMapper productMapper;

    @Autowired
    ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,
                       CustomProductRepository customProductRepository){
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.customProductRepository = customProductRepository;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean saveProductItem(ProductDTO productDTO) throws GiftItemException {

        ProductDoc productDoc = productRepository.save(productMapper.dtoToModel(productDTO));

        if(productDoc.getItemId() != null){
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     * Add the Regex pattern from here to repository
     * Add / to before and after of itemName
     */
    @Override
    public List<ProductDTO> findItemByRegexName(String itemName) throws Exception {

        return customProductRepository.findItemByRegexpName(itemName)
                .stream()
                .map(itemDoc -> productMapper.modelToDto(itemDoc))
                .collect(Collectors.toList());
    }

    /**
     * @param productDTO
     * @return
     * @throws Exception
     */
    @Override
    public ProductDTO updateGiftItem(ProductDTO productDTO) throws GiftItemException{

        ProductDoc productDoc = productRepository.findByItemId(productDTO.getItemId());
        Integer itemQuantity = productDoc.getItemQuantity();

        if(productDoc != null){

            //Copy to be update values to Document
            productMapper.updateModelWithToBeUpdatedValues(productDoc, productDTO);

            //If quantity required to update it should increment instead of update the value
            if (productDTO.getItemQuantity() != null) {
                productDoc.setItemQuantity(productDoc.getItemQuantity() + itemQuantity);
            }

            //Update the date when status changed
            //toBeUpdatedDoc.getItemStatus().setStatusUpdatedDate(new Date());

            return productMapper.modelToDto(customProductRepository.updateGiftItem(productDoc));

        } else {
            throw new GiftItemNotFoundException("Gift Item not found with item id : " + productDTO.getItemId());
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductDTO findItemByItemId(String itemId) throws GiftItemException {

        ProductDoc productDoc = productRepository.findByItemId(itemId);

        return productMapper.modelToDto(productDoc);
    }


}
