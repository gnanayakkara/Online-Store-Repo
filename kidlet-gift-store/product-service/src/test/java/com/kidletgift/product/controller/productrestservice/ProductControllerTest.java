package com.kidletgift.product.controller.productrestservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kidletgift.product.controller.productrestservice.request.*;
import com.kidletgift.product.controller.productrestservice.response.ProductResponse;
import com.kidletgift.product.model.productdoc.ProductDoc;
import com.kidletgift.product.repository.product.ProductRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Arrays;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductControllerTest {

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
    private ProductRepository productRepository;

    @Test
    @Order(1)
    void saveItem() throws Exception {

        ProductRequest productRequest = getGiftItemRequest();
        String productRequestString = objectMapper.writeValueAsString(productRequest);

        mockMvc.perform(MockMvcRequestBuilders.post("/product/additem")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequestString))
                .andExpect(status().isCreated());

        Assertions.assertEquals(1, productRepository.findAll().size());
    }

    @Test
    @Order(2)
    void findGiftItem() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/product/findItem/pki"))
                .andExpect(status().isOk())
                .andExpect(result -> {
                    Assertions.assertNotNull(result.getResponse());
                    Assertions.assertEquals(1, objectMapper.readValue(result.getResponse().getContentAsString(), ProductResponse.class).getGiftItems().size());
                });
    }

    @Test
    @Order(3)
    void updateItem() throws Exception {

        ProductDoc productDoc = productRepository.findByItemCode("G_BS_1234");
        Assertions.assertNotNull(productDoc);

        ProductRequest giftItemUpdateRequest = getGiftItemUpdateRequest();
        giftItemUpdateRequest.setItemId(productDoc.getItemId());

        mockMvc.perform(MockMvcRequestBuilders.put("/product/updateItem")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(giftItemUpdateRequest)))
                .andExpect(status().isCreated())
                .andExpect(result -> {
                    Assertions.assertNotNull(result.getResponse());
                    Assertions.assertNotNull(objectMapper.readValue(result.getResponse().getContentAsString(), ProductResponse.class).getGiftItem());
                });

    }

    @AfterAll
    void cleanDB(){
        productRepository.deleteAll();
    }

    /**
     * Get Gift item request for unit testing
     * @return
     */
    private ProductRequest getGiftItemRequest(){

        ItemSubGroup itemSubGroup = ItemSubGroup.builder()
                .itemId("3123")
                .itemName("red")
                .itemImgUrl("testUrl")
                .build();

        return ProductRequest.builder()
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

    private ProductRequest getGiftItemUpdateRequest(){
        return ProductRequest.builder()
                .itemCategory("Baby Cloths")
                .itemGender("Boy")
                .itemCode("G_BS_1234")
                .itemName("Napkin")
                .itemQuantity(20)
                .itemPrice(10.5)
                .build();
    }

}
