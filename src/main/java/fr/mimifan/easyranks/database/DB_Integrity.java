package fr.mimifan.easyranks.database;

import com.mongodb.BasicDBObject;
import fr.mimifan.easyranks.Main;
import fr.mimifan.easyranks.dbManager.DB_Client;

import static fr.mimifan.easyranks.dbManager.DB_Client.mongodb;

public class DB_Integrity {
    public static void verifyDB() {
        // TODO If database doesn't exists
         String players_collection = Main.getInstance().getConfig().getString("database.players_collection");
         String ranks_collection = Main.getInstance().getConfig().getString("database.ranks_collection");
         String ranksPerm_collection = Main.getInstance().getConfig().getString("database.ranksPermissions_collection");
        if(!mongodb.collectionExists(players_collection)) mongodb.createCollection(players_collection, null);
        if(!mongodb.collectionExists(ranksPerm_collection)) mongodb.createCollection(ranksPerm_collection, null);
        if(!mongodb.collectionExists(ranks_collection)){
            mongodb.createCollection(ranks_collection, null);
            final String rank = Main.getInstance().getConfig().getString("database.default-rank");
            BasicDBObject rankAdded = new BasicDBObject("rank", rank);
            rankAdded.put("rank", rank);
            rankAdded.put("prefix", Main.getInstance().getConfig().getString("database.default-rank-prefix"));
            rankAdded.put("priority", 1);
            DB_Client.ranks_collection.insert(rankAdded);
        }
    }
}
