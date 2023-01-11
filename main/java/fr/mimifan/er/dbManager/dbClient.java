package fr.mimifan.er.dbManager;

import java.util.ArrayList;
import com.mongodb.*;
import fr.mimifan.er.Main;

import java.util.List;

public class dbClient {

    public static MongoClient mongoClient(String address) {
        ServerAddress addr = new ServerAddress(address, Main.getInstance().getConfig().getInt("database.port"));
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(MongoCredential.createCredential(Main.getInstance().getConfig().getString("database.username"), Main.getInstance().getConfig().getString("database.databaseName"), Main.getInstance().getConfig().getString("database.password").toCharArray()));
        return new MongoClient(addr, credentials);
    }

    public static MongoClient client = mongoClient(Main.getInstance().getConfig().getString("database.host"));
    @SuppressWarnings("deprecation")
    public static DB mongodb = client.getDB(Main.getInstance().getConfig().getString("database.databaseName"));
    public static DBCollection players_collection = mongodb.getCollection(Main.getInstance().getConfig().getString("database.players_collection"));
    public static DBCollection ranks_collection = mongodb.getCollection(Main.getInstance().getConfig().getString("database.ranks_collection"));

}
