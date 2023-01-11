package fr.mimifan.er;

import fr.mimifan.er.commands.CommandEasyRanks;
import fr.mimifan.er.events.EventChat;
import fr.mimifan.er.events.EventJoin;
import fr.mimifan.er.events.EventLeave;
import fr.mimifan.er.guis.dbManager.MainManagerListener;
import fr.mimifan.er.guis.dbManager.playersManager.PlayerManagerListener;
import fr.mimifan.er.guis.dbManager.ranksManager.RanksManagerListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Main extends JavaPlugin {

    private static Main instance;
    public static HashMap<Player, String> writingPrefix = new HashMap<>();

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getLogger().info("EasyRanks starting...");
        getLogger().info("Registering events");

        getCommand("easyranks").setExecutor(new CommandEasyRanks());

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EventJoin(), this);
        pm.registerEvents(new EventLeave(), this);
        pm.registerEvents(new EventChat(), this);

        pm.registerEvents(new MainManagerListener(), this);
        pm.registerEvents(new PlayerManagerListener(), this);
        pm.registerEvents(new RanksManagerListener(), this);

    }

    public static Main getInstance(){
        return instance;
    }
    public HashMap<Player, String> getWritingPrefix() {
        return writingPrefix;
    }

    @Override
    public void onDisable() {
        getLogger().info("Shutting down...");
    }
}
