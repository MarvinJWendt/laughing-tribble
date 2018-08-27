package de.hardcorepvp;

import de.hardcorepvp.commands.*;
import de.hardcorepvp.database.DatabaseManager;
import de.hardcorepvp.file.ConfigFile;
import de.hardcorepvp.listener.*;
import de.hardcorepvp.manager.RankingManager;
import de.hardcorepvp.manager.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private static Main instance;
	private static ConfigFile configFile;
	private static DatabaseManager databaseManager;
	private static UserManager userManager;
	private static RankingManager rankingManager;

	@Override
	public void onEnable() {
		instance = this;
		configFile = new ConfigFile();
		registerCommands();
		registerListeners();
		databaseManager = new DatabaseManager(configFile);
		if (!databaseManager.connect()) {
			System.out.println("Keine Datenbank Verbindung!");
			Bukkit.getScheduler().runTaskLater(this, () -> Bukkit.getServer().setWhitelist(true), 60L);
			return;
		}
		userManager = new UserManager();
		rankingManager = new RankingManager();

	}

	@Override
	public void onDisable() {
		databaseManager.disconnect();
	}

	private void registerCommands() {

		getCommand("heal").setExecutor(new CommandHeal());
		getCommand("feed").setExecutor(new CommandFeed());
		getCommand("craft").setExecutor(new CommandCraft());
		getCommand("fix").setExecutor(new CommandFix());
		getCommand("ifix").setExecutor(new CommandiFix());
		getCommand("enderchest").setExecutor(new CommandEnderchest());
		getCommand("hat").setExecutor(new CommandHat());
		getCommand("rename").setExecutor(new CommandRename());
		getCommand("stack").setExecutor(new CommandStack());
		getCommand("pvp").setExecutor(new CommandPvP());
		getCommand("invsee").setExecutor(new CommandInvsee());
	}

	private void registerListeners() {
		this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(), this);
		this.getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerChatListener(), this);
		this.getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
	}

	public static Main getInstance() {
		return instance;
	}

	public static ConfigFile getConfigFile() {
		return configFile;
	}

	public static DatabaseManager getDatabaseManager() {
		return databaseManager;
	}

	public static UserManager getUserManager() {
		return userManager;
	}

	public static RankingManager getRankingManager() {
		return rankingManager;
	}
}
