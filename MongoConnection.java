package com.songs.seed.util;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoConnection {

    static MongoClient mongoClient = null;

    static String DATABASE = "test";

    public static MongoDatabase getMongoDatabase(){
        return getMongoClient().getDatabase(DATABASE);
    }

    public static MongoClient getMongoClient() {

        if(mongoClient == null){
            String connectionString = "mongodb://localhost:27017";
            ServerApi serverApi = ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build();

            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(new ConnectionString(connectionString))
                    .serverApi(serverApi)
                    .build();
            mongoClient =  MongoClients.create(settings);
            return  mongoClient;
        }
        return mongoClient;
    }
}
