package de.hardcorepvp.model;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

public class Functions {

    public static void removeNegEffects(Player player) {
	for (PotionEffect effects : player.getActivePotionEffects()) {
	    for (NegativeEffects negEffects : NegativeEffects.values()) {
		if (effects.getType().getName().equalsIgnoreCase(negEffects.name())) {
		    player.removePotionEffect(effects.getType());
		}
	    }
	}
    }

    public static void fixItems(ItemStack[] items) {
	for (int i = 0; i < items.length; i++) {
	    if (items[i] != null) {
		items[i].setDurability((short) 0);
	    }
	}
    }

    public static void renameItemInHand(Player player, String name) {

	String itemname = ChatColor.translateAlternateColorCodes('&', name);

	ItemStack item = player.getItemInHand();
	ItemMeta itemmeta = item.getItemMeta();

	itemmeta.setDisplayName(itemname);
	item.setItemMeta(itemmeta);

    }

    public static void stackItems(ItemStack[] contents) {

	for (int i = 0; i < contents.length; i++) {
	    ItemStack current = contents[i];
	    if ((current != null) && (current.getType() != Material.AIR) && (current.getAmount() > 0)) {
		if (current.getAmount() < 64) {
		    int needed = 64 - current.getAmount();
		    for (int i2 = i + 1; i2 < contents.length; i2++) {
			ItemStack nextCurrent = contents[i2];
			if ((nextCurrent != null) && (nextCurrent.getType() != Material.AIR)
				&& (nextCurrent.getAmount() > 0)) {
			    if ((current.getType() == nextCurrent.getType())
				    && (current.getDurability() == nextCurrent.getDurability())
				    && (((current.getItemMeta() == null) && (nextCurrent.getItemMeta() == null))
					    || ((current.getItemMeta() != null)
						    && (current.getItemMeta().equals(nextCurrent.getItemMeta()))))) {
				if (nextCurrent.getAmount() > needed) {
				    current.setAmount(64);
				    nextCurrent.setAmount(nextCurrent.getAmount() - needed);
				    break;
				}
				contents[i2] = null;
				current.setAmount(current.getAmount() + nextCurrent.getAmount());
				needed = 64 - current.getAmount();

			    }
			}
		    }
		}
	    }
	}
    }
}