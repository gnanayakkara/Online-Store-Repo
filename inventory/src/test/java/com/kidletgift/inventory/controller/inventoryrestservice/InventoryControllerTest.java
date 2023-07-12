package com.kidletgift.inventory.controller.inventoryrestservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kidletgift.inventory.controller.inventoryrestservice.request.*;
import com.kidletgift.inventory.controller.inventoryrestservice.response.InventoryResponse;
import com.kidletgift.inventory.model.inventoryDoc.InventoryDoc;
import com.kidletgift.inventory.repository.inventory.InventoryRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.io.DataInput;
import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InventoryControllerTest {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:6.0.6"));

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
        mongoDBContainer.start();
        dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Test
    @Order(1)
    void saveItem() throws Exception {

        InventoryRequest inventoryRequest = getGiftItemRequest();
        String inventoryRequestString = objectMapper.writeValueAsString(inventoryRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/inventory/additem")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(inventoryRequestString))
                .andExpect(status().isCreated());

        Assertions.assertEquals(1, inventoryRepository.findAll().size());
    }

    @Test
    @Order(2)
    void findGiftItem() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/inventory/findItem/pki"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Assertions.assertNotNull(result.getResponse());
                    Assertions.assertEquals(1, objectMapper.readValue(result.getResponse().getContentAsString(), InventoryResponse.class).getGiftItems().size());
                });
    }

    @Test
    @Order(3)
    void updateItem() throws Exception {

        InventoryDoc inventoryDoc = inventoryRepository.findByItemCode("G_BS_1234");
        Assertions.assertNotNull(inventoryDoc);

        InventoryRequest giftItemUpdateRequest = getGiftItemUpdateRequest();
        giftItemUpdateRequest.setItemId(inventoryDoc.getItemId());

        mockMvc.perform(MockMvcRequestBuilders.put("/inventory/updateItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(giftItemUpdateRequest)))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    Assertions.assertNotNull(result.getResponse());
                    Assertions.assertNotNull(objectMapper.readValue(result.getResponse().getContentAsString(),InventoryResponse.class).getGiftItem());
                });

    }

    @AfterAll
    void cleanDB(){
        inventoryRepository.deleteAll();
    }

    /**
     * Get Gift item request for unit testing
     * @return
     */
    private InventoryRequest getGiftItemRequest(){

        ItemSubGroup itemSubGroup = ItemSubGroup.builder()
                .itemId("3123")
                .itemName("red")
                .itemImgUrl("testUrl")
                .build();

        return InventoryRequest.builder()
                .itemCategory("Baby Cloths")
                .itemGender("Girl")
                .itemCode("G_BS_1234")
                .itemName("Napkin")
                .itemQuantity(20)
                .itemPrice(10.5)
                .itemSubGroups(Arrays.asList(itemSubGroup,itemSubGroup))
                .itemFeatures(Arrays.asList("Feature 1", "Feature 2", "Feature 3"))
                .ageCategory(AgeCategory.builder().from(1).to(4).build())
                .stockId("ST43521")
                .itemImages(Arrays.asList(ItemImages.builder().imageOrder(1).imageUrl("Test URL").build()))
                .itemDimension(ItemDimension.builder().length(3.0).width(3.0).height(3.0).weight(12.4).sizeMetric("cm").weightMetric("g").build())
                .technicalDetails(Arrays.asList(TechnicalDetails.builder().key("made from ").value("fabric").build()))
                .itemDescriptionWithImages(Arrays.asList(ItemDescriptionWithImages.builder()
                        .imageOrder(1).imageDescription("Testing Image").imageUrl("Test URL").build()))
                .build();
    }

    private InventoryRequest getGiftItemUpdateRequest(){
        return InventoryRequest.builder()
                .itemCategory("Baby Cloths")
                .itemGender("Boy")
                .itemCode("G_BS_1234")
                .itemName("Napkin")
                .itemQuantity(20)
                .itemPrice(10.5)
                .build();
    }

}
