package fr.mrqsdf.buildmovementplugin;

import fr.mrqsdf.buildmovementplugin.command.*;
import fr.mrqsdf.buildmovementplugin.event.listener.PlayerGetLocListener;
import fr.mrqsdf.buildmovementplugin.event.listener.PlayerJoinListener;
import fr.mrqsdf.buildmovementplugin.res.JsonGestion;
import fr.mrqsdf.buildmovementplugin.res.MaterialRessource;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class BuildMovementPlugin extends JavaPlugin {

    public static BuildMovementPlugin instance;
    public static FileConfiguration config;
    public static boolean isDefaultMode;
    public static int tickInterval;

    @Override
    public void onEnable() {
        String version = Bukkit.getBukkitVersion().split("-")[0];
        System.out.println(version);
        switch (version)
        {
            case "1.8.1", "1.8.2", "1.8.3", "1.8.4", "1.8.5", "1.8.6", "1.8.7", "1.8.8", "1.8.9", "1.9", "1.9.1", "1.9.2", "1.9.3", "1.9.4", "1.10", "1.10.1", "1.10.2", "1.11", "1.11.1", "1.11.2", "1.12", "1.12.1", "1.12.2":
                MaterialRessource.hoe = Material.getMaterial("WOOD_HOE");
                MaterialRessource.shovel = Material.getMaterial("WOOD_SPADE");
                break;
            case "1.13.1", "1.13.2", "1.14", "1.14.1", "1.14.2", "1.14.3", "1.14.4", "1.15", "1.15.1", "1.15.2", "1.16", "1.16.1", "1.16.2", "1.16.3", "1.16.4", "1.16.5", "1.17", "1.17.1","1.18","1.18.1","1.18.2","1.19","1.19.1","1.19.2","1.19.3","1.19.4" :{
                MaterialRessource.hoe = Material.LEGACY_WOOD_HOE;
                MaterialRessource.shovel = Material.LEGACY_WOOD_SPADE;
                break;
            }
            case "1.20","1.20.1","1.20.2","1.20.3","1.20.4":
                MaterialRessource.hoe = Material.WOODEN_HOE;
                MaterialRessource.shovel = Material.WOODEN_SHOVEL;
                break;
            default:
                getLogger().severe("Unsupported version");
                Bukkit.getPluginManager().disablePlugin(this);
                break;
        }
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
        Bukkit.getPluginCommand("activatebuild").setExecutor(new ActivateBuildPlaceCommand());
        Bukkit.getPluginCommand("delbuild").setExecutor(new DelBuildCommand());
        Bukkit.getPluginCommand("settickinterval").setExecutor(new ChangeTickCommand());
        new GameRun().runTaskTimer(this, 0, tickInterval);
        getLogger().info("BuildMovementPlugin has been enabled");
    }

    @Override
    public void onDisable() {
        JsonGestion.saveBuildData();
        getLogger().info("BuildMovementPlugin has been disabled");
    }

}
