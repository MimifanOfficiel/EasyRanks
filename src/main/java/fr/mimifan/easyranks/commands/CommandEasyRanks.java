package fr.mimifan.easyranks.commands;

import fr.mimifan.easyranks.Main;
import fr.mimifan.easyranks.guis.dbManager.MainManager;
import fr.mimifan.easyranks.guis.dbManager.playersManager.PlayerManager;
import fr.mimifan.easyranks.guis.dbManager.ranksManager.RanksManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEasyRanks implements CommandExecutor {

    public static String prefix = Main.getInstance().getConfig().getString("command-usage.prefix").replace("&","§");
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            if(!(sender instanceof Player)) {sender.sendMessage("The Console can not use this command with no arguments."); return false;}
            ((Player) sender).openInventory(MainManager.mainGUI());
        } else if(args.length == 1){
            if(!(sender instanceof Player)) {sender.sendMessage("The Console can not use this command with no arguments."); return false;}
            if(args[0].equalsIgnoreCase("ranks")){
                ((Player) sender).openInventory(RanksManager.mainRanksGUI());
            } else if(args[0].equalsIgnoreCase("players")){
                ((Player) sender).openInventory(PlayerManager.MainPlayerManager());
            } else if(args[0].equalsIgnoreCase("help")){
                sender.sendMessage("Coming soon");
            } else {
                ((Player) sender).openInventory(MainManager.mainGUI());
            }
        } else if(args.length == 2){
            Cmd2Args.execute(sender, args);
        } else if(args.length == 3){
            Cmd3Args.execute(sender, args);
        } else if(args.length == 4){
            Cmd4Args.execute(sender, args);
        }
        if(args.length > 8) sender.sendMessage(" §cused too much arguments while using the command §4/easyranks§r");
        return false;
    }
}
