package fr.mimifan.easyranks.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Players {

    public static boolean isPlayerOnline(String name){
        Player player = Bukkit.getPlayer(name);
        if(player != null){
            return player.isOnline();
        }
        return false;
    }



}
