package fr.mimifan.er.guis.dbManager.playersManager;

import fr.mimifan.er.Main;
import fr.mimifan.er.database.getData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerManager {

    public static Inventory MainPlayerManager(){
        int nbPlayers = Bukkit.getOnlinePlayers().size();
        double size = (double) nbPlayers / 9;
        if ((size - (int) size) != 0) size = size + 1;
        Inventory inv = Bukkit.createInventory(null, (int) size * 9, Main.getInstance().getConfig().getString("guis.playerManager.title").replace("&","§"));
        Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(Main.class), new Runnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()){
                    ItemStack p = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
                    SkullMeta pM = (SkullMeta) p.getItemMeta();
                    String rank = getData.getInfos(player.getUniqueId()).rank;
                    String prefix = getData.getInfos(rank).prefix.replace("&", "§");
                    pM.setDisplayName(Main.getInstance().getConfig().getString("server-settings.rank-prefix")
                            .replace("&","§")
                            .replace("%prefix%", prefix.replace("&","§"))
                            .replace("%rank%", rank) + " " + player.getName());
                    pM.setOwner(player.getName());
                    p.setItemMeta(pM);
                    inv.addItem(p);
                }
            }
        });
        return inv;
    }

}
