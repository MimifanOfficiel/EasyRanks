package fr.mimifan.easyranks.guis.dbManager.ranksManager;

import com.sun.org.apache.xpath.internal.operations.Bool;
import fr.mimifan.easyranks.Main;
import fr.mimifan.easyranks.database.DB_getData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RanksManager {

    private static HashMap<Player, Boolean> inRankManager = new HashMap<Player, Boolean>();

    public static HashMap<Player, Boolean> getPlayersInRankManager(){
        return inRankManager;
    }

    public static Inventory mainRanksGUI(){
        double size = (double) DB_getData.getRanks().size() / 9;
        if ((size - (int) size) != 0) size = size + 1;
        Inventory inv = Bukkit.createInventory(null, (int) size * 9, Main.getInstance().getConfig().getString("guis.ranksManager.title").replace("&","§"));
        Bukkit.getScheduler().runTaskAsynchronously(Main.getPlugin(Main.class), new Runnable() {
            @Override
            public void run() {
                for(String rank : DB_getData.getRanks()){
                    ItemStack item = new ItemStack(Material.PAPER, 1);
                    ItemMeta itemM = item.getItemMeta();
                    itemM.setDisplayName(Main.getInstance().getConfig().getString("guis.ranksManager.prefix")
                            .replace("&","§")
                            .replace("%rank%", rank)
                            .replace("%prefix%", DB_getData.getInfos(rank).prefix.replace("&","§")));
                    item.setItemMeta(itemM);
                    inv.addItem(item);
                }
            }
        });
        return inv;
    }

    public static Inventory rankEdition(String rank){
        Inventory inv = Bukkit.createInventory(null, 45, Main.getInstance().getConfig().getString("guis.ranksManager.title").replace("&","§") + " §8§l> §r" + rank);
        ItemStack color = new ItemStack(Material.WOOL);
        ItemMeta colorItemMeta = color.getItemMeta();
        colorItemMeta.setDisplayName(Main.getInstance().getConfig().getString("guis.ranksManager.color").replace("&","§"));
        color.setItemMeta(colorItemMeta);
        ItemStack name = new ItemStack(Material.PAPER);
        ItemMeta nameItemMeta = name.getItemMeta();
        nameItemMeta.setDisplayName(Main.getInstance().getConfig().getString("guis.ranksManager.name").replace("&","§"));
        name.setItemMeta(nameItemMeta);

        inv.setItem(0, color);
        inv.setItem(2, name);
        return inv;
    }

    public static Inventory changeColor(String rank){
        Inventory inv = Bukkit.createInventory(null, 45, Main.getInstance().getConfig().getString("guis.ranksManager.title").replace("&","§"));
        List<ItemStack> items = new ArrayList<>();
        for(short i=0; i < 17; i++){
            ItemStack color = new ItemStack(Material.INK_SACK, i);
            items.add(color);
        }
        ItemMeta one      = items.get(0).getItemMeta();  one.setDisplayName("§fBWhite");        items.get(0).setItemMeta(one);
        ItemMeta two      = items.get(1).getItemMeta();  two.setDisplayName("§6Orange");        items.get(1).setItemMeta(two);
        ItemMeta three    = items.get(2).getItemMeta();  three.setDisplayName("§dMagenta");     items.get(2).setItemMeta(three);
        ItemMeta four     = items.get(3).getItemMeta();  four.setDisplayName("§bLight Blue");   items.get(3).setItemMeta(four);
        ItemMeta five     = items.get(4).getItemMeta();  five.setDisplayName("§eYellow");       items.get(4).setItemMeta(five);
        ItemMeta six      = items.get(5).getItemMeta();  six.setDisplayName("§aGreen");         items.get(5).setItemMeta(six);
        ItemMeta seven    = items.get(6).getItemMeta();  seven.setDisplayName("§dPink");        items.get(6).setItemMeta(seven);
        ItemMeta eight    = items.get(7).getItemMeta();  eight.setDisplayName("§8Gray");        items.get(7).setItemMeta(eight);
        ItemMeta nine     = items.get(8).getItemMeta();  nine.setDisplayName("§7Light Gray");   items.get(8).setItemMeta(nine);
        ItemMeta ten      = items.get(9).getItemMeta();  ten.setDisplayName("§3Cyan");          items.get(9).setItemMeta(ten);
        ItemMeta eleven   = items.get(10).getItemMeta(); eleven.setDisplayName("§5Purple");     items.get(10).setItemMeta(eleven);
        ItemMeta twelve   = items.get(11).getItemMeta(); twelve.setDisplayName("§1Blue");       items.get(11).setItemMeta(twelve);
        ItemMeta thirteen = items.get(12).getItemMeta(); thirteen.setDisplayName("§cRed");      items.get(12).setItemMeta(thirteen);
        ItemMeta fourteen = items.get(13).getItemMeta(); fourteen.setDisplayName("§2Dark Red"); items.get(13).setItemMeta(fourteen);
        ItemMeta fifteen  = items.get(14).getItemMeta(); fifteen.setDisplayName("§4Dark Red");  items.get(14).setItemMeta(fifteen);
        ItemMeta sixteen  = items.get(15).getItemMeta(); sixteen.setDisplayName("§0Black");     items.get(15).setItemMeta(sixteen);

        inv.setItem(0, items.get(0)); inv.setItem(2, items.get(1)); inv.setItem(4, items.get(2)); inv.setItem(6, items.get(3)); inv.setItem(8, items.get(4));
        inv.setItem(10, items.get(5)); inv.setItem(12, items.get(6)); inv.setItem(14, items.get(7)); inv.setItem(16, items.get(8));
        inv.setItem(18, items.get(9)); inv.setItem(20, items.get(10)); inv.setItem(22, items.get(11)); inv.setItem(24, items.get(12)); inv.setItem(26, items.get(13));
        inv.setItem(28, items.get(14)); inv.setItem(34, items.get(15));
        return inv;
    }

}
