package fr.mimifan.easyranks.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Sorts;
import fr.mimifan.easyranks.commands.CommandEasyRanks;
import fr.mimifan.easyranks.dbManager.DB_Client;
import net.md_5.bungee.api.chat.TextComponent;
import org.bson.Document;
import org.bson.conversions.Bson;
import net.md_5.bungee.api.chat.*;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class RankManipulation {

    public static void changePrefix(String rank, String prefix){
        BasicDBObject updated = new BasicDBObject("rank", rank);
        DBObject found = DB_Client.ranks_collection.findOne(updated);
        updated.append("$set", new BasicDBObject("prefix", prefix));
        DB_Client.ranks_collection.update(found, updated);
    }

    public static void updateRank(String rank, String documentName, String newDocumentValue){
        BasicDBObject updated = new BasicDBObject("rank", rank);
        DBObject found = DB_Client.ranks_collection.findOne(updated);
        updated.append("$set", new BasicDBObject(documentName, newDocumentValue));
        DB_Client.ranks_collection.update(found, updated);
    }

    public static void addRank(String rank, String prefix) {
        BasicDBObject rankAdded = new BasicDBObject("rank", rank);
        rankAdded.put("rank", rank);
        if(prefix == null) rankAdded.put("prefix", "&7");
        else rankAdded.put("prefix", prefix);
        List<Bson> pipeline = Arrays.asList(
                Aggregates.sort(Sorts.descending("priority")),
                Aggregates.limit(1)
        );
        Document result = DB_Client.ranksOfDoc_Collection.aggregate(pipeline).first();
        double maxPriority = result.getDouble("priority");
        rankAdded.put("priority", maxPriority + 1);
        DB_Client.ranks_collection.insert(rankAdded);
    }

    public static void removeRank(String rank) {
        BasicDBObject remove = new BasicDBObject("rank", rank);
        DBObject found = DB_Client.ranks_collection.findOne(remove);
        DB_Client.ranks_collection.remove(found);
    }

    public static String listRanks(short page) {
        short perPage = 4;
        short currentPage = page;
        StringBuilder list = new StringBuilder();

        long nbPagesMax = (long) (DB_Client.ranksOfDoc_Collection.countDocuments() / perPage)+1;
        if(currentPage > nbPagesMax) return listRanks((short) nbPagesMax);
        list.append(CommandEasyRanks.prefix + "Rank List " + currentPage + "§b/§7" + nbPagesMax + "\n\n");

        page--;
        short skip = (short) (page*perPage);
        FindIterable<Document> iterable = DB_Client.ranksOfDoc_Collection.find().skip(skip).limit(perPage);
        iterable.sort(Sorts.ascending("priority")).iterator();
        MongoCursor<Document> cursor = iterable.iterator();
        try {
            while (cursor.hasNext()) {
                String rank = cursor.next().getString("rank");
                String prefix = DB_getData.getInfos(rank).prefix;
                double priority = DB_getData.getInfos(rank).priority;
                list.append("§7Rank: " + prefix.replace("&","§") + rank + "\n");
                list.append("§7Prefix: " + prefix.replace("&","§") + prefix + "\n");
                list.append("§7Priority: " + (int) priority + "\n");
                list.append("§f---------\n");
            }
        } finally {
            cursor.close();
        }

        return list.toString();
    }

    public static void changePage(final Player player, short page){
        TextComponent cPage = new TextComponent("§7Change page");
        TextComponent pMun = new TextComponent(" « ");
        TextComponent pPun = new TextComponent(" » ");
        if(page==1){
            pMun.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/er rank list"));
            pMun.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7/er rank list").create()));
            pPun.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/er rank list 2"));
            pPun.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7/er rank list 2").create()));
        } else {
            pMun.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/er rank list " + (page-1)));
            pMun.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7/er rank list " + (page-1)).create()));
            pPun.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/er rank list " + (page+1)));
            pPun.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7/er rank list " + (page+1)).create()));
        }
        player.spigot().sendMessage(cPage, pMun, new TextComponent("/"), pPun);
    }

}
