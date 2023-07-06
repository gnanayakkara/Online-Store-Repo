package com.kidletgift.inventory.controller.inventoryrestservice;

import com.kidletgift.inventory.controller.inventoryrestservice.request.InventoryRequest;
import com.kidletgift.inventory.controller.inventoryrestservice.response.GiftItem;
import com.kidletgift.inventory.controller.inventoryrestservice.response.InventoryResponse;
import com.kidletgift.inventory.dto.inventory.InventoryDTO;
import com.kidletgift.inventory.mapper.inventory.InventoryMapper;
import com.kidletgift.inventory.model.inventoryDoc.InventoryDoc;
import com.kidletgift.inventory.service.inventory.serviceinterface.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryMapper inventoryMapper;

    private final InventoryService inventoryService;

    @Autowired
    InventoryController(InventoryMapper inventoryMapper,InventoryService inventoryService){
        this.inventoryMapper = inventoryMapper;
        this.inventoryService = inventoryService;
    }

    @PostMapping("/additem")
    public ResponseEntity<InventoryResponse> saveItem(@RequestBody InventoryRequest inventoryRequest) throws Exception {

        Boolean isItemSaved = inventoryService.saveInventoryItem(inventoryMapper.requestToDTO(inventoryRequest));

        return getInventoryResponseResponseEntity(isItemSaved);
    }

    @GetMapping("/findItem/{itemName}")
    public ResponseEntity<InventoryResponse> findGiftItem(@PathVariable String itemName) throws Exception {

        List<GiftItem> giftItems;

        giftItems = inventoryService.findItemByRegexName(itemName)
                .stream()
                .map(inventoryMapper::dtoToGiftItem)
                .collect(Collectors.toList());

        InventoryResponse inventoryResponse = InventoryResponse.builder()
                .status("00")
                .giftItems(giftItems)
                .build();

        return new ResponseEntity<>(inventoryResponse,HttpStatus.OK);

    }

    @PutMapping("/updateItem")
    public ResponseEntity<InventoryResponse> updateItem(@RequestBody InventoryRequest inventoryRequest) throws Exception {

        InventoryDTO inventoryDTO = inventoryMapper.requestToDTO(inventoryRequest);

        //Update the Gift Item
        inventoryDTO = inventoryService.updateGiftItem(inventoryDTO);

        InventoryResponse inventoryResponse = InventoryResponse.builder()
                .status("00")
                .giftItem(inventoryMapper.dtoToGiftItem(inventoryDTO))
                .build();

        return new ResponseEntity<InventoryResponse>(inventoryResponse, HttpStatus.CREATED);

    }

    private ResponseEntity<InventoryResponse> getInventoryResponseResponseEntity(Boolean isItemSaved) {

        InventoryResponse inventoryResponse = null;

        if (isItemSaved){

            inventoryResponse = InventoryResponse.builder()
                    .status("00")
                    .build();

            return new ResponseEntity<InventoryResponse>(inventoryResponse, HttpStatus.CREATED);

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
