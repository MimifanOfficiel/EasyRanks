package fr.mimifan.easyranks.commands;

import fr.mimifan.easyranks.Main;
import fr.mimifan.easyranks.database.RankManipulation;
import fr.mimifan.easyranks.database.DB_getData;
import fr.mimifan.easyranks.dbManager.DB_Client;
import fr.mimifan.easyranks.guis.dbManager.MainManager;
import fr.mimifan.easyranks.guis.dbManager.playersManager.PlayerManager;
import fr.mimifan.easyranks.guis.dbManager.ranksManager.RanksManager;
import fr.mimifan.easyranks.utils.Players;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cmd2Args {

    public static void execute(final CommandSender sender, String[] args){
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(args[0].equalsIgnoreCase("player")){
                if(Players.isPlayerOnline(args[1])) {
                    player.openInventory(PlayerManager.PlayerEdition(args[1]));
                } else sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.player-not-online").replace("&","§"));
            } else if(args[0].equalsIgnoreCase("rank")){
                if(DB_getData.isDataInDB(DB_Client.ranks_collection,"rank", args[1])){
                    player.openInventory(RanksManager.rankEdition(args[1]));
                } else if(args[1].equalsIgnoreCase("list")){
                    player.sendMessage(RankManipulation.listRanks((short) 1));
                    RankManipulation.changePage(player, (short) 1);
                } else {
                    sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.rank-no-exists").replace("&","§"));
                }
            } else {
               player.openInventory(MainManager.mainGUI());
            }
        } else {
            if(args[0].equalsIgnoreCase("rank")) {
                if (args[1].equalsIgnoreCase("list")) {
                    sender.sendMessage(RankManipulation.listRanks((short) 1));
                }
            } else sender.sendMessage("§cNeeds to be a player to use the command this way.");
        }
    }

}
