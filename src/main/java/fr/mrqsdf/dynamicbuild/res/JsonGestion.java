package fr.mrqsdf.dynamicbuild.res;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.mrqsdf.dynamicbuild.DynamicBuildPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

public class JsonGestion {

    public static void saveBuildData() {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        try {
            FileWriter writer = new FileWriter(DynamicBuildPlugin.instance.getDataFolder() + "/buildData.json");
            writer.write(gson.toJson(BuildData.buildDataMap));
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void loadBuildData() {
        Gson gson = new Gson();
        File file = new File(DynamicBuildPlugin.instance.getDataFolder() + "/");
        if (!file.exists()){
            file.mkdirs();
        }
        File f = new File(DynamicBuildPlugin.instance.getDataFolder() + "/buildData.json");
        if (!f.exists()){
            System.out.println("File not Found");
        }
        try {
            FileReader fileReader = new FileReader(DynamicBuildPlugin.instance.getDataFolder() + "/buildData.json");
            Object o = JsonParser.parseReader(fileReader);
            JsonObject jsonObject = (JsonObject) o;
            String json = jsonObject.toString();
            Type empMapType = new TypeToken<Map<String , BuildData>>() {}.getType();
            BuildData.buildDataMap = gson.fromJson(json, empMapType);
        } catch (Exception e){
            DynamicBuildPlugin.instance.getLogger().warning("No data found, creating a new file");
        }


    }

}
