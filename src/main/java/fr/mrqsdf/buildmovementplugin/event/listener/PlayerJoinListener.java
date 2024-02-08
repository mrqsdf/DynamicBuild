package fr.mrqsdf.buildmovementplugin.event.listener;

import fr.mrqsdf.buildmovementplugin.BuildMovementPlugin;
import fr.mrqsdf.buildmovementplugin.res.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if (BuildMovementPlugin.isDefaultMode){
            PlayerData.playerIsBuilding.putIfAbsent(event.getPlayer().getUniqueId(), true);
        } else {
            PlayerData.playerIsBuilding.putIfAbsent(event.getPlayer().getUniqueId(), false);
        }
    }


}
