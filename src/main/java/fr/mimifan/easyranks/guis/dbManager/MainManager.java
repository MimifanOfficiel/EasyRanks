package fr.mimifan.easyranks.guis.dbManager;

import fr.mimifan.easyranks.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class MainManager {

    public static Inventory mainGUI(){
        Inventory inv = Bukkit.createInventory(null, 9, Main.getInstance().getConfig().getString("guis.mainManager.title").replace("&","§"));
        Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(Main.class), new Runnable() {
            @Override
            public void run() {
                ItemStack ranks = new ItemStack(Material.matchMaterial(Main.getInstance().getConfig().getString("guis.mainManager.items.ranks.item")));
                ItemStack players = new ItemStack(Material.matchMaterial(Main.getInstance().getConfig().getString("guis.mainManager.items.players.item")), 1, (short) SkullType.PLAYER.ordinal());

                ItemMeta rM = ranks.getItemMeta();
                SkullMeta spM = (SkullMeta) players.getItemMeta();

                rM.setDisplayName(Main.getInstance().getConfig().getString("guis.mainManager.items.ranks.name").replace("&","§"));

                spM.setDisplayName(Main.getInstance().getConfig().getString("guis.mainManager.items.players.name").replace("&","§"));
                spM.setOwner(Main.getInstance().getConfig().getString("guis.mainManager.items.players.owner"));

                ranks.setItemMeta(rM);
                players.setItemMeta(spM);

                inv.setItem(2, ranks);
                inv.setItem(6, players);
            }
        });
        return inv;
    }

}
