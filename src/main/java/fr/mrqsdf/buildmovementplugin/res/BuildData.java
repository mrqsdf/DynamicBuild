package fr.mrqsdf.buildmovementplugin.res;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildData implements Serializable {

    public List<Build> buildList = new ArrayList<>();
    public int frameTick = 0;
    public transient int actualFrameTick = 0;
    public transient int actualFrame = 0;
    public boolean airReplace = false;
    public boolean activated = false;

    public static Map<String ,BuildData> buildDataMap = new HashMap<>();

}
