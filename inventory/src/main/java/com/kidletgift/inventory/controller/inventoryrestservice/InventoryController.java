package com.kidletgift.inventory.controller.inventoryrestservice;

import com.kidletgift.inventory.controller.inventoryrestservice.request.InventoryRequest;
import com.kidletgift.inventory.controller.inventoryrestservice.response.GiftItem;
import com.kidletgift.inventory.controller.inventoryrestservice.response.InventoryResponse;
import com.kidletgift.inventory.mapper.inventory.InventoryMapper;
import com.kidletgift.inventory.service.inventory.serviceinterface.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private InventoryMapper inventoryMapper;

    private InventoryService inventoryService;

    @PostMapping("/additem")
    public ResponseEntity<InventoryResponse> saveItem(@RequestBody InventoryRequest inventoryRequest) throws Exception {

        Boolean isItemSaved = inventoryService.saveInventoryItem(inventoryMapper.RequestToDTO(inventoryRequest));

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

    @GetMapping("/findItem/{itemName}")
    public ResponseEntity<InventoryResponse> findGiftItem(String itemName) throws Exception {

        List<GiftItem> giftItems = new ArrayList<>();

        giftItems = inventoryService.findItemByRegexName(itemName)
                .stream()
                .map(item -> inventoryMapper.dtoToGiftItem(item))
                .collect(Collectors.toList());

        InventoryResponse inventoryResponse = InventoryResponse.builder()
                .status("00")
                .giftItems(giftItems)
                .build();

        return new ResponseEntity<>(inventoryResponse,HttpStatus.OK);

    }
}
