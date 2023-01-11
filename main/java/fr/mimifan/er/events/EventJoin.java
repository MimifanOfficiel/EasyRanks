package fr.mimifan.er.events;

import fr.mimifan.er.Main;
import fr.mimifan.er.database.UserManipulation;
import fr.mimifan.er.database.getData;
import fr.mimifan.er.dbManager.dbClient;
import fr.mimifan.er.utils.strings.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Date;

public class EventJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        if(getData.isDataInDB(dbClient.players_collection, "uuid", player.getUniqueId().toString())){
            Date now = new Date();
            UserManipulation.updateUser(player.getUniqueId(), "last-login", now);
        } else {
            Bukkit.broadcastMessage("Joueur pas trouvé dans la bdd");
            UserManipulation.createUser(player.getUniqueId(), player.getName());
            StringBuilder msg = StringUtils.getConfigText("newcoming.welcome-message", player);
            if(Main.getInstance().getConfig().getBoolean("newcoming.broadcast")){
                Bukkit.broadcastMessage(msg.toString());
            } else {
                player.sendMessage(msg.toString());
            }
        }
        //TODO UPDATE PLAYER PERMISSIONS
        UserManipulation.updateUser(player.getUniqueId(), "currently-logged", true);
        String rank = getData.getInfos(player.getUniqueId()).rank;
        String prefix = getData.getInfos(rank).prefix.replace("&","§");
        String displayName = prefix + "§l" + rank + " " + prefix + player.getName();
        player.setDisplayName(displayName);
        player.setPlayerListName(displayName);
        StringBuilder jmsg = StringUtils.getConfigText("newcoming.join-message", player);
        event.setJoinMessage(jmsg.toString());
    }

}
