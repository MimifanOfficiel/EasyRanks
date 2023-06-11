package fr.mimifan.easyranks;

import fr.mimifan.easyranks.commands.CommandEasyRanks;
import fr.mimifan.easyranks.events.EventChat;
import fr.mimifan.easyranks.events.EventJoin;
import fr.mimifan.easyranks.events.EventLeave;
import fr.mimifan.easyranks.guis.dbManager.MainManagerListener;
import fr.mimifan.easyranks.guis.dbManager.playersManager.PlayerManagerListener;
import fr.mimifan.easyranks.guis.dbManager.ranksManager.RanksManagerListener;
import fr.mimifan.easyranks.database.DB_Integrity;
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

        //DB_Integrity.verifyDB();

        getCommand("easyranks").setExecutor(new CommandEasyRanks());

        getLogger().info("Registering events");
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
