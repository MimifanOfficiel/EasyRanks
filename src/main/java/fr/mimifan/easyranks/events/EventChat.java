package fr.mimifan.easyranks.events;

import fr.mimifan.easyranks.database.DB_getData;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EventChat implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event){
        String rank = DB_getData.getInfos(event.getPlayer().getUniqueId()).rank;
        String prefix = DB_getData.getInfos(rank).prefix.replace("&","§");
        event.setFormat(prefix + "§l" + rank + " " + prefix + event.getPlayer().getName() + " §f§l» §f" + ChatColor.translateAlternateColorCodes('&', event.getMessage()));
    }

}
