package fr.mimifan.easyranks.events;

import fr.mimifan.easyranks.Main;
import fr.mimifan.easyranks.database.UserManipulation;
import fr.mimifan.easyranks.database.DB_getData;
import fr.mimifan.easyranks.dbManager.DB_Client;
import fr.mimifan.easyranks.utils.strings.StringUtils;
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
            if(DB_getData.isDataInDB(DB_Client.players_collection, "uuid", player.getUniqueId().toString())){
                Date now = new Date();
                UserManipulation.updateUser(player.getUniqueId(), "last-login", now);
                StringBuilder jmsg = StringUtils.getConfigText("newcoming.join-message", player);
                event.setJoinMessage(jmsg.toString());
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
            UserManipulation.updateUser(player.getUniqueId(), "currently-logged", true);
            String rank = DB_getData.getInfos(player.getUniqueId()).rank;
            String prefix = DB_getData.getInfos(rank).prefix.replace("&","§");
            UserManipulation.updatePlayersRank(player, rank);

        }

    }