package de.hardcorepvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import de.hardcorepvp.model.Functions;
import de.hardcorepvp.model.Messages;;

public class CommandFix implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	if (!(sender instanceof Player)) {
	    return false;
	}

	Player player = (Player) sender;

	Functions.fixItems(player.getInventory().getContents());
	Functions.fixItems(player.getInventory().getArmorContents());
	player.sendMessage(Messages.formatMessage("Deine Items wurden repariert"));
	return true;
    }
}