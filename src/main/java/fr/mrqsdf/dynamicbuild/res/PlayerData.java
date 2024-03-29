package fr.mrqsdf.dynamicbuild.res;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    public static final Map<UUID, PlayerLoc> playerLocs = new HashMap<>();
    public static final Map<UUID, Boolean> playerIsBuilding = new HashMap<>();

}
