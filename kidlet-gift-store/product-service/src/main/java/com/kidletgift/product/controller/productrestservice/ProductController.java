package com.kidletgift.product.controller.productrestservice;

import com.kidletgift.product.controller.productrestservice.request.ProductRequest;
import com.kidletgift.product.controller.productrestservice.response.GiftItem;
import com.kidletgift.product.controller.productrestservice.response.ProductResponse;
import com.kidletgift.product.dto.product.ProductDTO;
import com.kidletgift.product.exception.GiftItemException;
import com.kidletgift.product.mapper.product.ProductMapper;
import com.kidletgift.product.service.product.serviceinterface.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductMapper productMapper;

    private final ProductService productService;

    @Autowired
    ProductController(ProductMapper productMapper, ProductService productService){
        this.productMapper = productMapper;
        this.productService = productService;
    }

    @PostMapping("/additem")
    public ResponseEntity<ProductResponse> saveItem(@RequestBody ProductRequest productRequest) throws GiftItemException {

        Boolean isItemSaved = productService.saveProductItem(productMapper.requestToDTO(productRequest));

        return getProductResponseResponseEntity(isItemSaved);
    }

    @Operation(summary = "Get the item/items by Gift Item name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found the item or request does not failed.",
                content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProductResponse.class))})
    })
    @GetMapping("/findItem/{itemName}")
    public ResponseEntity<ProductResponse> findGiftItem(@Parameter(description = "Name of Gift Item")
            @PathVariable String itemName) throws Exception {

        List<GiftItem> giftItems;

        giftItems = productService.findItemByRegexName(itemName)
                .stream()
                .map(productMapper::dtoToGiftItem)
                .collect(Collectors.toList());

        ProductResponse productResponse = ProductResponse.builder()
                .status("00")
                .giftItems(giftItems)
                .build();

        return new ResponseEntity<>(productResponse,HttpStatus.OK);

    }

    @Operation(summary = "Get the item by itemId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found the item or request does not failed.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class))})
    })
    @GetMapping("/{itemId}")
    public ResponseEntity<ProductResponse> findGiftItemByItemId(@PathVariable String itemId) throws GiftItemException {

        ProductDTO productDTO = productService.findItemByItemId(itemId);
        ProductResponse productResponse = ProductResponse.builder()
                .status("00")
                .giftItem(productMapper.dtoToGiftItem(productDTO))
                .build();

        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update item. This function will work like a universal update. Add everything to be update with itemId")
    @PutMapping("/updateItem")
    public ResponseEntity<ProductResponse> updateItem(@RequestBody ProductRequest productRequest) throws GiftItemException {

        ProductDTO productDTO = productMapper.requestToDTO(productRequest);

        //Update the Gift Item
        productDTO = productService.updateGiftItem(productDTO);

        ProductResponse productResponse = ProductResponse.builder()
                .status("00")
                .giftItem(productMapper.dtoToGiftItem(productDTO))
                .build();

        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);

    }

    private ResponseEntity<ProductResponse> getProductResponseResponseEntity(Boolean isItemSaved) {

        ProductResponse productResponse = null;

        if (isItemSaved){

            productResponse = ProductResponse.builder()
                    .status("00")
                    .build();

            return new ResponseEntity<ProductResponse>(productResponse, HttpStatus.CREATED);

        } else {

            productResponse = productResponse.builder()
                    .status("01")
                    .errorCode("SAVED_ERROR")
                    .errorDescription("Item save service failure")
                    .build();

            return new ResponseEntity<ProductResponse>(productResponse,HttpStatus.CONFLICT);
        }
    }
}
