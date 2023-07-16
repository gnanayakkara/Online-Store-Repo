package com.kidletgift.product.config;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;


@Component
public class MongoDBConnectionFactory {

    @Value("${spring.data.mongodb.database}")
    String dbName;

    @Value("${spring.data.mongodb.uri}")
    String uri;

    /**
     * https://www.mongodb.com/docs/drivers/java/sync/current/fundamentals/data-formats/document-data-format-pojo/
     * @return
     * @throws Exception
     */
    public MongoDatabase getMongoDB() throws Exception {

        MongoClient mongoClient = MongoClients.create(uri);

        //Get Codec Provider to convert document to pojo class
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

        //You only need to configure one of these -> withCodecRegistry(pojoCodecRegistry)
        MongoDatabase database = mongoClient.getDatabase(dbName).withCodecRegistry(pojoCodecRegistry);

        return database;
    }

}
