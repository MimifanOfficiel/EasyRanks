package fr.mimifan.er.events;

import fr.mimifan.er.database.UserManipulation;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Date;

public class EventLeave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event){
        Player player = event.getPlayer();
        Date now = new Date();
        UserManipulation.updateUser(player.getUniqueId(), "last-logout", now);
        UserManipulation.updateUser(player.getUniqueId(), "currently-logged", false);
    }

}
