package fr.mrqsdf.buildmovementplugin;

import fr.mrqsdf.buildmovementplugin.command.ActivateModeCommand;
import fr.mrqsdf.buildmovementplugin.command.AddBuildCommand;
import fr.mrqsdf.buildmovementplugin.event.listener.PlayerGetLocListener;
import fr.mrqsdf.buildmovementplugin.event.listener.PlayerJoinListener;
import fr.mrqsdf.buildmovementplugin.res.JsonGestion;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class BuildMovementPlugin extends JavaPlugin {

    public static BuildMovementPlugin instance;
    public static FileConfiguration config;
    public static boolean isDefaultMode;
    public static int tickInterval;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        config = getConfig();
        JsonGestion.loadBuildData();
        isDefaultMode = config.getBoolean("defaultMode");
        tickInterval = config.getInt("tickInterval");
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerGetLocListener(), this);

        Bukkit.getPluginCommand("buildmode").setExecutor(new ActivateModeCommand());
        Bukkit.getPluginCommand("addbuild").setExecutor(new AddBuildCommand());
        new GameRun().runTaskTimer(this, 0, tickInterval);
        getLogger().info("BuildMovementPlugin has been enabled");
    }

    @Override
    public void onDisable() {
        JsonGestion.saveBuildData();
        getLogger().info("BuildMovementPlugin has been disabled");
    }

}
