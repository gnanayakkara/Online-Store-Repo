package com.kidletgift.inventory.controller.inventoryrestservice;

import com.kidletgift.inventory.controller.inventoryrestservice.request.InventoryRequest;
import com.kidletgift.inventory.controller.inventoryrestservice.response.InventoryResponse;
import com.kidletgift.inventory.mapper.inventory.InventoryMapper;
import com.kidletgift.inventory.service.inventory.serviceinterface.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    InventoryService inventoryService;

    @PostMapping("/additem")
    public ResponseEntity<InventoryResponse> saveItem(@RequestBody InventoryRequest inventoryRequest) throws Exception {

        Boolean isItemSaved = inventoryService.saveInventoryItem(inventoryMapper.modelToDTO(inventoryRequest));

        InventoryResponse inventoryResponse = null;

        if (isItemSaved){

            inventoryResponse = InventoryResponse.builder()
                    .status("00")
                    .build();

            return new ResponseEntity<InventoryResponse>(inventoryResponse,HttpStatus.CREATED);

        } else {

            inventoryResponse = inventoryResponse.builder()
                    .status("01")
                    .errorCode("SAVED_ERROR")
                    .errorDescription("Item save service failure")
                    .build();

            return new ResponseEntity<InventoryResponse>(inventoryResponse,HttpStatus.CONFLICT);
        }

    }
}
