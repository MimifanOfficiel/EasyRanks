package fr.mimifan.easyranks.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Sorts;
import fr.mimifan.easyranks.database.players.playersData;
import fr.mimifan.easyranks.database.ranks.DataRankInfos;
import fr.mimifan.easyranks.dbManager.DB_Client;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DB_getData {

    public static boolean isDataInDB(DBCollection collection, String documentName, String documentValue) {
        return !(collection.findOne(new BasicDBObject(documentName, documentValue))==null);
    }

    public static playersData getInfos(UUID uuid){
        DBObject r = new BasicDBObject("uuid", uuid.toString());
        DBObject found = DB_Client.players_collection.findOne(r);
        if(found==null) throw new NullPointerException("The user with uuid " + uuid.toString() + " doesn't exist in database.");
        return getPlayersData(found);
    }

    private static playersData getPlayersData(DBObject found) {
        String username = (String) found.get("username");
        String rank = (String) found.get("rank");
        boolean premium = (boolean) found.get("premium");
        boolean logged = (boolean) found.get("currently-logged");


        return new playersData(username, rank, premium, logged);
    }

    public static playersData getInfosFromName(String playerName){
        DBObject r = new BasicDBObject("username", playerName);
        DBObject found = DB_Client.players_collection.findOne(r);
        if(found==null) throw new NullPointerException("The user " + playerName + " doesn't exist in database.");
        return getPlayersData(found);
    }

    public static DataRankInfos getInfos(String rank){
        DBObject r = new BasicDBObject("rank", rank);
        DBObject found = DB_Client.ranks_collection.findOne(r);
        if(found==null) throw new NullPointerException("The rank " + rank + " doesn't exist in database.");
        String rankName = (String) found.get("rank");
        String prefix = (String) found.get("prefix");
        double priority = (double) found.get("priority");

        return new DataRankInfos(rankName, prefix, priority);
    }

    public static List<String> getRanks(){
        List<String> ranks = new ArrayList<>();
        DBCursor ranksCursor = DB_Client.ranks_collection.find().sort(new BasicDBObject("priority", 1));
        for(DBObject document : ranksCursor) {
            ranks.add((String)document.get("rank"));
        }
        return ranks;
    }

}
