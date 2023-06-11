package fr.mimifan.easyranks.utils.strings;

import fr.mimifan.easyranks.Main;
import org.bukkit.entity.Player;

public class StringUtils {

    public static StringBuilder getConfigText(String path, Player player){
        StringBuilder txt = new StringBuilder();
        for(String part : Main.getInstance().getConfig().getStringList(path)) {
            txt.append(part.replace("&","§")
                    .replace("%player%", player.getName())
                    .replace("%server%", Main.getInstance().getConfig().getString("server-settings.name"))
                    .replace("%version%", Main.getInstance().getConfig().getString("server-settings.version"))+ "\n");
        }
        return txt;
    }
    public static StringBuilder getConfigText(String path){
        StringBuilder txt = new StringBuilder();
        for(String part : Main.getInstance().getConfig().getStringList(path)) {
            txt.append(part.replace("&","§")
               .replace("%server%", Main.getInstance().getConfig().getString("server-settings.name"))
               .replace("%version%", Main.getInstance().getConfig().getString("server-settings.version"))+ "\n");
        }
        return txt;
    }

}
