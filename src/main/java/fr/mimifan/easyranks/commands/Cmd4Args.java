package fr.mimifan.easyranks.commands;

import fr.mimifan.easyranks.Main;
import fr.mimifan.easyranks.database.RankManipulation;
import fr.mimifan.easyranks.database.UserManipulation;
import fr.mimifan.easyranks.database.DB_getData;
import fr.mimifan.easyranks.dbManager.DB_Client;
import fr.mimifan.easyranks.utils.Players;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Cmd4Args {

    public static void execute(final CommandSender sender, final String[] args){
        if(args[0].equalsIgnoreCase("player")){
            if(Players.isPlayerOnline(args[1])) {
                if(args[2].equalsIgnoreCase("rank")){
                    if(DB_getData.isDataInDB(DB_Client.ranks_collection, "rank", args[3])){
                        Player target = Bukkit.getPlayer(args[1]);
                        UserManipulation.updateUser(target.getUniqueId(), "rank", args[3]);
                        UserManipulation.updatePlayersRank(target, args[3]);
                        UserManipulation.updatePlayerPermissions(target, args[3]);
                        sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.player-changed-rank").replace("&","§").replace("%player%", target.getName()).replace("%rank%", args[3]));
                    } else sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.rank-no-exists").replace("&","§"));
                }
            } else sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.player-not-online").replace("&","§"));
        } else if(args[0].equalsIgnoreCase("rank")){
            if(args[1].equalsIgnoreCase("prefix")){
                if(DB_getData.isDataInDB(DB_Client.ranks_collection, "rank", args[2])){
                    RankManipulation.changePrefix(args[2], args[3]);
                    for(Player p : Bukkit.getOnlinePlayers()){
                        if(DB_getData.getInfos(p.getUniqueId()).rank == args[2]){
                            UserManipulation.updatePlayersRank(p, args[2]);
                        }
                    }
                    sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.rank-prefix-changed").replace("&","§").replace("%rank%", args[1]).replace("%new%", args[3].replace("&","§") + args[1]));
                }
            } else if(args[1].equalsIgnoreCase("add")){
                if(DB_getData.isDataInDB(DB_Client.ranks_collection, "rank", args[2])){
                    sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.rank-already-exists").replace("&","§"));
                } else {
                    RankManipulation.addRank(args[2], args[3]);
                    sender.sendMessage(CommandEasyRanks.prefix + Main.getInstance().getConfig().getString("command-usage.rank-added").replace("&","§").replace("%rank%",args[2]));
                }
            }
        }
    }

}
