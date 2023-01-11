package fr.mimifan.er.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import fr.mimifan.er.dbManager.dbClient;

public class RankManipulation {

    public static void changePrefix(String rank, String prefix){
        BasicDBObject updated = new BasicDBObject("rank", rank);
        DBObject found = dbClient.ranks_collection.findOne(updated);
        if (found == null) throw new NullPointerException("The Rank " + rank + " doesn't exist in database, can not update.");
        updated.append("$set", new BasicDBObject("prefix", prefix));
        dbClient.ranks_collection.update(found, updated);
    }

    public static void updateRank(String rank, String documentName, String newDocumentValue){
        BasicDBObject updated = new BasicDBObject("rank", rank);
        DBObject found = dbClient.ranks_collection.findOne(updated);
        if (found == null) throw new NullPointerException("The Rank " + rank + " doesn't exist in database, can not update.");
        updated.append("$set", new BasicDBObject(documentName, newDocumentValue));
        dbClient.ranks_collection.update(found, updated);
    }

}
