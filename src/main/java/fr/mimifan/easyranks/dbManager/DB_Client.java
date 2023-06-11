package fr.mimifan.easyranks.dbManager;

import java.util.ArrayList;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import fr.mimifan.easyranks.Main;
import org.bson.Document;

import java.util.List;

public class DB_Client {

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
    public static DBCollection ranksPermissions_collection = mongodb.getCollection(Main.getInstance().getConfig().getString("database.ranksPermissions_collection"));

    public static MongoDatabase db = client.getDatabase(Main.getInstance().getConfig().getString("database.databaseName"));
    public static MongoCollection<Document> ranksOfDoc_Collection = db.getCollection(Main.getInstance().getConfig().getString("database.ranks_collection"));

}
