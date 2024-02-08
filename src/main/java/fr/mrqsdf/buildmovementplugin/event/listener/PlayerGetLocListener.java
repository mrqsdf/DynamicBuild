package fr.mrqsdf.buildmovementplugin.event.listener;

import fr.mrqsdf.buildmovementplugin.res.PlayerData;
import fr.mrqsdf.buildmovementplugin.res.PlayerLoc;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerGetLocListener implements Listener {

    @EventHandler
    public void onPlayerGetLocListener(BlockBreakEvent event){
        Player player = event.getPlayer();
        if (!PlayerData.playerIsBuilding.get(player.getUniqueId())) return;
        if (player.getInventory().getItemInMainHand().getType() != Material.WOODEN_HOE) return;
        Location loc = event.getBlock().getLocation();
        PlayerLoc playerLoc = PlayerData.playerLocs.getOrDefault(player.getUniqueId(), new PlayerLoc());
        playerLoc.locLeftClick = loc;
        PlayerData.playerLocs.put(player.getUniqueId(), playerLoc);
        int blockX1 = playerLoc.locLeftClick.getBlockX();
        int blockY1 = playerLoc.locLeftClick.getBlockY();
        int blockZ1 = playerLoc.locLeftClick.getBlockZ();
        Location loc2 = playerLoc.locRightClick;
        if (loc2 == null){
            player.sendMessage("§6Pos1 set to " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ()+ "("+ 1 +" blocks)");
            event.setCancelled(true);
            return;
        }
        int blockX2 = playerLoc.locRightClick.getBlockX();
        int blockY2 = playerLoc.locRightClick.getBlockY();
        int blockZ2 = playerLoc.locRightClick.getBlockZ();
        int longueur = Math.abs(blockX1 - blockX2) == 0 ? 1 : Math.abs(blockX1 - blockX2);
        int largeur = Math.abs(blockY1 - blockY2) == 0 ? 1 : Math.abs(blockY1 - blockY2);
        int hauteur = Math.abs(blockZ1 - blockZ2) == 0 ? 1 : Math.abs(blockZ1 - blockZ2);
        int blockTotal = longueur * largeur * hauteur;
        player.sendMessage("§6Pos1 set to " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "("+ blockTotal +" blocks)");
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerGetLocListener(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (!PlayerData.playerIsBuilding.get(player.getUniqueId())) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (player.getInventory().getItemInMainHand().getType() != Material.WOODEN_HOE) return;
        Location loc = event.getClickedBlock().getLocation();
        PlayerLoc playerLoc = PlayerData.playerLocs.getOrDefault(player.getUniqueId(), new PlayerLoc());
        playerLoc.locRightClick = loc;
        PlayerData.playerLocs.put(player.getUniqueId(), playerLoc);
        Location loc2 = playerLoc.locLeftClick;
        if (loc2 == null){
            player.sendMessage("§6Pos2 set to " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ()+ "("+ 1 +" blocks)");
            event.setCancelled(true);
            return;
        }
        int blockX1 = playerLoc.locLeftClick.getBlockX();
        int blockY1 = playerLoc.locLeftClick.getBlockY();
        int blockZ1 = playerLoc.locLeftClick.getBlockZ();
        int blockX2 = playerLoc.locRightClick.getBlockX();
        int blockY2 = playerLoc.locRightClick.getBlockY();
        int blockZ2 = playerLoc.locRightClick.getBlockZ();
        int longueur = Math.abs(blockX1 - blockX2) == 0 ? 1 : Math.abs(blockX1 - blockX2);
        int largeur = Math.abs(blockY1 - blockY2) == 0 ? 1 : Math.abs(blockY1 - blockY2);
        int hauteur = Math.abs(blockZ1 - blockZ2) == 0 ? 1 : Math.abs(blockZ1 - blockZ2);
        int blockTotal = longueur * largeur * hauteur;
        player.sendMessage("§6Pos2 set to " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "("+ blockTotal +" blocks)");
        event.setCancelled(true);
    }

    @EventHandler
    public void getCenterLoc(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (!PlayerData.playerIsBuilding.get(player.getUniqueId())) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (player.getInventory().getItemInMainHand().getType() != Material.WOODEN_SHOVEL) return;
        Location loc = event.getClickedBlock().getLocation();
        PlayerLoc playerLoc = PlayerData.playerLocs.getOrDefault(player.getUniqueId(), new PlayerLoc());
        playerLoc.origineLoc = loc;
        PlayerData.playerLocs.put(player.getUniqueId(), playerLoc);
        player.sendMessage("§6Origine set to " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
        event.setCancelled(true);
    }



}
