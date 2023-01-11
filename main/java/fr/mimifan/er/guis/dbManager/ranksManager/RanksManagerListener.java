package fr.mimifan.er.guis.dbManager.ranksManager;

import fr.mimifan.er.Main;
import fr.mimifan.er.database.getData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class RanksManagerListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (event.getClickedInventory() == null) return;
        if (event.getCurrentItem() == null) return;
        Inventory inventory = event.getClickedInventory();
        ItemStack itemStack = event.getCurrentItem();
        int clickedslot = event.getSlot();
        if (itemStack != null) {
            if(event.getView().getTitle().contains(Main.getInstance().getConfig().getString("guis.ranksManager.title").replace("&","§"))){
                if(event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getConfig().getString("guis.ranksManager.title").replace("&","§"))){
                    if(itemStack.getType() == Material.matchMaterial(Main.getInstance().getConfig().getString("guis.ranksManager.rankItems"))){
                        for(String rank : getData.getRanks()){
                            String rankprefix = getData.getInfos(rank).prefix;
                            if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(Main.getInstance().getConfig().getString("guis.ranksManager.prefix")
                                    .replace("&","§")
                                    .replace("%prefix%", getData.getInfos(rank).prefix.replace("&","§"))
                                    .replace("%rank%", rank))){
                                player.closeInventory();
                                player.openInventory(RanksManager.rankEdition(rank));
                            }
                        }
                    }
                } else if(event.getView().getTitle().length() > Main.getInstance().getConfig().getString("guis.ranksManager.title").replace("&","§").length()){

                }

            }
        }
    }

}
