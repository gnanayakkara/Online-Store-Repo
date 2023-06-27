package com.kidletgift.inventory.controller;

import com.kidletgift.inventory.controller.inventory.InventoryRequest;
import com.kidletgift.inventory.controller.inventory.InventoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @PostMapping("/save")
    public ResponseEntity<InventoryResponse> saveItem(@RequestBody InventoryRequest inventoryRequest){


        return null;
    }
}
