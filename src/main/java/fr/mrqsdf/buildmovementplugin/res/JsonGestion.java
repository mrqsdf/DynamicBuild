package fr.mrqsdf.buildmovementplugin.res;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.mrqsdf.buildmovementplugin.BuildMovementPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class JsonGestion {

    public static void saveBuildData() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try {
            FileWriter writer = new FileWriter(BuildMovementPlugin.instance.getDataFolder() + "/buildData.json");
            writer.write(gson.toJson(BuildData.buildDataMap));
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void loadBuildData() {
        Gson gson = new Gson();
        File file = new File(BuildMovementPlugin.instance.getDataFolder() + "/");
        if (!file.exists()){
            file.mkdirs();
        }
        File f = new File(BuildMovementPlugin.instance.getDataFolder() + "/buildData.json");
        if (!f.exists()){
            System.out.println("File not Found");
        }
        try {
            FileReader fileReader = new FileReader(BuildMovementPlugin.instance.getDataFolder() + "/buildData.json");
            Object o = JsonParser.parseReader(fileReader);
            JsonObject jsonObject = (JsonObject) o;
            String json = jsonObject.toString();
            Type empMapType = new TypeToken<Map<String , BuildData>>() {}.getType();
            BuildData.buildDataMap = gson.fromJson(json, empMapType);
        } catch (Exception e){
            BuildMovementPlugin.instance.getLogger().warning("No data found, creating a new file");
        }


    }

}
