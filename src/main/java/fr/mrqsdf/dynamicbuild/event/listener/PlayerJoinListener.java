package fr.mrqsdf.dynamicbuild.event.listener;

import fr.mrqsdf.dynamicbuild.DynamicBuild;
import fr.mrqsdf.dynamicbuild.res.PlayerData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if (DynamicBuild.isDefaultMode){
            PlayerData.playerIsBuilding.putIfAbsent(event.getPlayer().getUniqueId(), true);
        } else {
            PlayerData.playerIsBuilding.putIfAbsent(event.getPlayer().getUniqueId(), false);
        }
    }


}
