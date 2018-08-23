package de.hardcorepvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import de.hardcorepvp.model.Messages;

public class CommandHat implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (!(sender instanceof Player)) {
	    return false;
	}

	Player player = (Player) sender;

	if (args.length == 0) {
	    player.getInventory().setHelmet(player.getItemInHand());
	    player.sendMessage(Messages.formatMessage("Du hast nun einen Hut"));
	    return true;
	}
	if (args.length > 0) {
	    player.sendMessage(Messages.formatMessage(Messages.TOO_MANY_ARGUMENTS));
	    return true;
	}

	return false;
    }

}