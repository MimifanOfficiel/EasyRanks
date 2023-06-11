package fr.mimifan.easyranks.guis.dbManager.ranksManager;

import fr.mimifan.easyranks.Main;
import fr.mimifan.easyranks.database.DB_getData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
            if(event.getView().getTitle().equalsIgnoreCase(Main.getInstance().getConfig().getString("guis.ranksManager.title").replace("&","§"))){
                if(RanksManager.getPlayersInRankManager().containsKey(player) && RanksManager.getPlayersInRankManager().containsValue(true)){
                    if(itemStack.getType() == Material.matchMaterial(Main.getInstance().getConfig().getString("guis.ranksManager.rankItems"))){
                        for(String rank : DB_getData.getRanks()){
                            String rankprefix = DB_getData.getInfos(rank).prefix;
                            if(itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(Main.getInstance().getConfig().getString("guis.ranksManager.prefix")
                                    .replace("&","§")
                                    .replace("%prefix%", DB_getData.getInfos(rank).prefix.replace("&","§"))
                                    .replace("%rank%", rank))){
                                player.closeInventory();
                                RanksManager.getPlayersInRankManager().put(player, true);
                                player.openInventory(RanksManager.rankEdition(rank));
                            }
                        }
                    }
                }
            } else if(event.getView().getTitle().length() > Main.getInstance().getConfig().getString("guis.ranksManager.title").replace("&","§").length()){

            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if(!RanksManager.getPlayersInRankManager().containsKey(event.getPlayer())) return;
        if(RanksManager.getPlayersInRankManager().containsValue(false)) return;
        RanksManager.getPlayersInRankManager().remove(event.getPlayer());
    }

}
