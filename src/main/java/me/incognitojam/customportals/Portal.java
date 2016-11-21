package me.incognitojam.customportals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

public class Portal {

    public static int PORTAL_MAX_DISTANCE = 64;
    public static int PORTAL_MAX_DISTANCE_SQUARED = PORTAL_MAX_DISTANCE * PORTAL_MAX_DISTANCE;
    public static int PORTAL_CREATION_RANGE = 24;

    private String portalWorld;
    private String targetWorld;

    private Location startLocation;
    private Location endLocation;

    private Location centerLocation;

    private Block[][] portalBlocks;

    public Portal(String portalWorld, String targetWorld, Block[][] portalBlocks) {
        this.portalWorld = portalWorld;
        this.targetWorld = targetWorld;
        this.startLocation = portalBlocks[0][0].getLocation();
//        System.out.println(portalBlocks[3][4]);
        this.endLocation = portalBlocks[3][4].getLocation();
        this.portalBlocks = portalBlocks;

        centerLocation = startLocation.add(endLocation.subtract(startLocation).multiply(0.5d));
    }

    public void ignitePortal() {
        Block[] innerPortalBlocks = new Block[]{
                portalBlocks[1][1],
                portalBlocks[2][1],
                portalBlocks[1][2],
                portalBlocks[2][2],
                portalBlocks[1][3],
                portalBlocks[2][3]
        };

        boolean northSouth = portalBlocks[0][0].getX() == portalBlocks[0][1].getX();
//        System.out.println((byte) (northSouth ? 1 : 0));

        // Create 'portal' within portal
        for (Block teleportBlock : innerPortalBlocks) {
            teleportBlock.setType(Material.PORTAL,false);
            teleportBlock.setData((byte) (northSouth ? 1 : 0), false);
        }
    }

    public void teleportPlayer(Player player) {
//        System.out.println("Teleporting " + player.getName() + " to " + portalBlocks[1][2].getLocation());
        player.teleport(portalBlocks[1][2].getLocation(), TeleportCause.PLUGIN);
    }

    public String getPortalWorld() {
        return portalWorld;
    }

    public String getTargetWorld() {
        return targetWorld;
    }

    public Block[][] getPortalBlocks() {
        return portalBlocks;
    }

    public Location getLocation() {
        return centerLocation;
    }

    public double getDistance(Location location) {
        location = location.clone();
        location.setY(centerLocation.getY());
        double distance = this.centerLocation.distanceSquared(location);
        return distance <= PORTAL_MAX_DISTANCE_SQUARED ? distance : -1;
    }

    public String toString() {
        return portalWorld + ";;" + targetWorld + ";;" + toString(startLocation) + ";;" + toString(endLocation) + ";;";
    }

    private String toString(Location location) {
        return location.getBlockX() + ";" + location.getBlockY() + ";" + location.getBlockZ();
    }
}
