package fr.mimifan.easyranks.guis.dbManager;

import fr.mimifan.easyranks.Main;
import fr.mimifan.easyranks.guis.dbManager.playersManager.PlayerManager;
import fr.mimifan.easyranks.guis.dbManager.ranksManager.RanksManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MainManagerListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(event.getClickedInventory() == null) return;
        if(event.getCurrentItem() == null) return;
        Inventory inventory = event.getClickedInventory();
        ItemStack itemStack = event.getCurrentItem();
        int clickedslot = event.getSlot();
        if(itemStack != null){
            if(event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getConfig().getString("guis.mainManager.title").replace("&","§"))){
                if(itemStack.getType() == Material.matchMaterial(Main.getInstance().getConfig().getString("guis.mainManager.items.ranks.item"))){
                    if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(Main.getInstance().getConfig().getString("guis.mainManager.items.ranks.name").replace("&","§"))){
                        event.setCancelled(true);
                        player.closeInventory();
                        RanksManager.getPlayersInRankManager().put(player, true);
                        player.openInventory(RanksManager.mainRanksGUI());
                    }
                } else if(itemStack.getType() == Material.matchMaterial(Main.getInstance().getConfig().getString("guis.mainManager.items.players.item"))){
                    if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(Main.getInstance().getConfig().getString("guis.mainManager.items.players.name").replace("&","§"))){
                        event.setCancelled(true);
                        player.closeInventory();
                        player.openInventory(PlayerManager.MainPlayerManager());
                    }
                }
            }
        }
    }
}
