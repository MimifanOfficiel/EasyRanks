package fr.mimifan.easyranks.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import fr.mimifan.easyranks.Main;
import fr.mimifan.easyranks.dbManager.DB_Client;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UserManipulation {

    public static void createUser(UUID uuid, String name){
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        DBObject newUser = new BasicDBObject("uuid", uuid.toString());
        newUser.put("uuid", uuid.toString());
        newUser.put("username", name);
        newUser.put("rank", Main.getInstance().getConfig().getString("database.default-rank"));
        newUser.put("premium", false);
        newUser.put("first-join", format.format(now));
        newUser.put("last-login", format.format(now));
        newUser.put("last-logout", null);
        newUser.put("currently-logged", true);

        DB_Client.players_collection.insert(newUser);
    }

    public static void updateUser(UUID uuid, String document, Object value) {
        DBObject user = new BasicDBObject("uuid", uuid.toString());
        DBObject found = DB_Client.players_collection.findOne(user);
        if (found == null) throw new NullPointerException("The user with uuid \" + uuid.toString() + \" doesn't exist in database, can not update.");
        BasicDBObject updated = new BasicDBObject("$set", user);
        updated.append("$set", new BasicDBObject(document, value));
        DB_Client.players_collection.update(found, updated);
    }

    public static void updatePlayersRank(Player player, String rank){
        String displayName = DB_getData.getInfos(rank).prefix.replace("&","§") + "§l" + rank + " " + DB_getData.getInfos(rank).prefix.replace("&","§") + player.getName();
        player.setDisplayName(displayName);
        player.setPlayerListName(displayName);
        updatePlayerPermissions(player, rank);
    }

    public static void updateUser(UUID uuid, String document, Date now){
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        DBObject user = new BasicDBObject("uuid", uuid.toString());
        DBObject found = DB_Client.players_collection.findOne(user);
        if(found==null) throw new NullPointerException("The user with uuid " + uuid.toString() + " doesn't exist in database, can not update last login.");
        if(document != "last-login" && document != "last-logout") throw new NullPointerException("This date row doesn't exist. Can not update date.");
        BasicDBObject updated = new BasicDBObject("$set", user);
        updated.append("$set", new BasicDBObject(document, format.format(now)));
        DB_Client.players_collection.update(found, updated);
    }

    public static void updatePlayerPermissions(Player player, String rank) {

    }
}
