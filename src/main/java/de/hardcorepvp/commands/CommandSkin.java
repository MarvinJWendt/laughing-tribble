package de.hardcorepvp.commands;

import de.hardcorepvp.utils.Messages;
import de.hardcorepvp.utils.Utils;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import net.minecraft.util.com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CommandSkin implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return false;
		}
		Player player = (Player) sender;

		if (args.length == 0) {
			try {
				String[] props = Utils.getSkinData(player.getName());
				GameProfile gp = ((CraftPlayer) player).getProfile();
				gp.getProperties().clear();
				gp.getProperties().put("textures", new Property("textures", props[0], props[1]));
				player.sendMessage("Du hast deinen Skin resetet");
				for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
					onlinePlayer.hidePlayer(player);
					onlinePlayer.showPlayer(player);
				}
			} catch (Exception exception) {
				player.sendMessage("Dein Skin konnte aufgrund von zu vielen Requests an die Mojang API nicht geladen werden!");
			}
			return true;
		}
		if (args.length == 1) {
			try {
				if (Bukkit.getPlayer(args[0]) != null) {
					Property props = Utils.getSkinData(Bukkit.getPlayer(args[0]));
					GameProfile gp = ((CraftPlayer) player).getProfile();
					gp.getProperties().clear();
					gp.getProperties().put("textures", new Property("textures", props.getValue(), props.getSignature()));
					player.sendMessage("Du hast nun einen neuen Skin");
					for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
						onlinePlayer.hidePlayer(player);
						onlinePlayer.showPlayer(player);
					}
				} else {
					String[] props = Utils.getSkinData(args[0]);
					GameProfile gp = ((CraftPlayer) player).getProfile();
					gp.getProperties().clear();
					gp.getProperties().put("textures", new Property("textures", props[0], props[1]));
					player.sendMessage("Du hast nun einen neuen Skin");
					for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
						onlinePlayer.hidePlayer(player);
						onlinePlayer.showPlayer(player);
					}
				}
			} catch (Exception exception) {
				player.sendMessage("Dein Skin konnte aufgrund von zu vielen Requests an die Mojang API nicht geladen werden!");
			}
			return true;
		}
		player.sendMessage(Messages.formatMessage(Messages.TOO_MANY_ARGUMENTS));
		return true;
	}
}

