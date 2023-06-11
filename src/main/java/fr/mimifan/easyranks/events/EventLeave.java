package fr.mimifan.easyranks.events;

import fr.mimifan.easyranks.database.UserManipulation;
import fr.mimifan.easyranks.utils.strings.StringUtils;
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
        StringBuilder lmsg = StringUtils.getConfigText("newcoming.leave-message", player);
        event.setQuitMessage(lmsg.toString());
    }

}
