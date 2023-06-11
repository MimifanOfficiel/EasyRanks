package fr.mimifan.easyranks.commands;

import fr.mimifan.easyranks.Main;
import fr.mimifan.easyranks.database.RankManipulation;
import fr.mimifan.easyranks.database.DB_getData;
import fr.mimifan.easyranks.dbManager.DB_Client;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cmd3Args {
    public static void execute(final CommandSender sender, final String[] args){
        if(args[0].equalsIgnoreCase("rank")){
             if(args[1].equalsIgnoreCase("add")){
                if(DB_getData.isDataInDB(DB_Client.ranks_collection, "rank", args[2])){
                    sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.rank-already-exists").replace("&","§"));
                } else {
                    RankManipulation.addRank(args[2], null);
                    sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.rank-added").replace("&","§").replace("%rank%",args[2]));
                }
            } else if(args[1].equalsIgnoreCase("remove")) {
                 if (DB_getData.isDataInDB(DB_Client.ranks_collection, "rank", args[2])) {
                     RankManipulation.removeRank(args[2]);
                     sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.rank-removed").replace("&", "§").replace("%rank%", args[2]));
                 } else {
                     sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.rank-no-exists").replace("&", "§").replace("%rank%", args[2]));
                 }
            } else if(args[1].equalsIgnoreCase("list")){
                try {
                    short page = Short.valueOf(args[2]);
                    if(page < 1){ sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.page-too-low")); return;}
                    sender.sendMessage(RankManipulation.listRanks(page));
                    if(sender instanceof Player) RankManipulation.changePage(((Player)sender), page);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                 }
            }
        } else if(args[0].equalsIgnoreCase("player")){

        }
    }

}
