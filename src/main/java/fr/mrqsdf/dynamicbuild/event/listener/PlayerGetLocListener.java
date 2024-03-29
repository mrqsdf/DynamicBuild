package fr.mrqsdf.dynamicbuild.event.listener;

import fr.mrqsdf.dynamicbuild.res.MaterialRessource;
import fr.mrqsdf.dynamicbuild.res.PlayerData;
import fr.mrqsdf.dynamicbuild.res.PlayerLoc;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class PlayerGetLocListener implements Listener {

    @EventHandler
    public void onPlayerGetLocListener(BlockBreakEvent event){
        Player player = event.getPlayer();
        System.out.println("test1");
        if (!PlayerData.playerIsBuilding.get(player.getUniqueId())) return;
        if (player.getInventory().getItemInMainHand().getType() != MaterialRessource.hoe) return;
        System.out.println("test2");
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
        int blockTotal = BlockCalcul(playerLoc, blockX1, blockY1, blockZ1);
        player.sendMessage("§6Pos1 set to " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "("+ blockTotal +" blocks)");
        event.setCancelled(true);
    }

    private int BlockCalcul(PlayerLoc playerLoc, int blockX1, int blockY1, int blockZ1) {
        int blockX2 = playerLoc.locRightClick.getBlockX();
        int blockY2 = playerLoc.locRightClick.getBlockY();
        int blockZ2 = playerLoc.locRightClick.getBlockZ();
        int longueur = Math.abs(blockX1 - blockX2) == 0 ? 1 : Math.abs(blockX1 - blockX2);
        int largeur = Math.abs(blockY1 - blockY2) == 0 ? 1 : Math.abs(blockY1 - blockY2);
        int hauteur = Math.abs(blockZ1 - blockZ2) == 0 ? 1 : Math.abs(blockZ1 - blockZ2);
        return longueur * largeur * hauteur;
    }

    @EventHandler
    public void onPlayerGetLocListener(PlayerInteractEvent event){
        Player player = event.getPlayer();
        System.out.println("test3");
        if (!PlayerData.playerIsBuilding.get(player.getUniqueId())) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        if (player.getInventory().getItemInMainHand().getType() != MaterialRessource.hoe) return;
        System.out.println("test4");
        Location loc = Objects.requireNonNull(event.getClickedBlock()).getLocation();
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
        int blockTotal = BlockCalcul(playerLoc, blockX1, blockY1, blockZ1);
        player.sendMessage("§6Pos2 set to " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "("+ blockTotal +" blocks)");
        event.setCancelled(true);
    }

    @EventHandler
    public void getCenterLoc(PlayerInteractEvent event){
        Player player = event.getPlayer();
        System.out.println("test5");
        if (!PlayerData.playerIsBuilding.get(player.getUniqueId())) return;
        System.out.println("test5.1");
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        System.out.println("test5.2");
        System.out.println(player.getInventory().getItemInMainHand().getType());
        System.out.println(MaterialRessource.shovel);
        if (player.getInventory().getItemInMainHand().getType() != MaterialRessource.shovel) return;
        System.out.println("test6");
        Location loc = Objects.requireNonNull(event.getClickedBlock()).getLocation();
        PlayerLoc playerLoc = PlayerData.playerLocs.getOrDefault(player.getUniqueId(), new PlayerLoc());
        playerLoc.origineLoc = loc;
        PlayerData.playerLocs.put(player.getUniqueId(), playerLoc);
        player.sendMessage("§6Origine set to " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ());
        event.setCancelled(true);
    }



}
