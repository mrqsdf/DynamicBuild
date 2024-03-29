package fr.mrqsdf.dynamicbuild.res;

import org.bukkit.Location;

public class Build {

    public int locX1;
    public int locY1;
    public int locZ1;
    public int locX2;
    public int locY2;
    public int locZ2;
    public int locX3;
    public int locY3;
    public int locZ3;
    public String world;

    public Build(Location loc1, Location loc2, Location loc3){
        this.locX1 = loc1.getBlockX();
        this.locY1 = loc1.getBlockY();
        this.locZ1 = loc1.getBlockZ();
        this.locX2 = loc2.getBlockX();
        this.locY2 = loc2.getBlockY();
        this.locZ2 = loc2.getBlockZ();
        this.locX3 = loc3.getBlockX();
        this.locY3 = loc3.getBlockY();
        this.locZ3 = loc3.getBlockZ();
        this.world = loc1.getWorld().getName();
    }

}
