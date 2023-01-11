package fr.mimifan.er.commands;

import fr.mimifan.er.guis.dbManager.MainManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandEasyRanks implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player)sender;
        if(args.length == 0){
            if(!(sender instanceof Player)) {sender.sendMessage("The Console can not use this command with no arguments."); return false;}
            player.openInventory(MainManager.mainGUI());
        }
        if(args.length > 8) throw new IndexOutOfBoundsException(sender.getName() + " §cused too much arguments while using the command §4/easyranks§r");
        return false;
    }
}
