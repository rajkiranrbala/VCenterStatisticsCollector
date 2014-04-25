package edu.cmpe283.hostCollector.config;

import com.google.gson.Gson;
import edu.cmpe283.hostCollector.model.ConfigFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

/**
 * User: vplociennik
 * Date: 11/22/13
 * Time: 3:30 AM
 */
public class Config {

    public static String ROOT;
    public static Integer SAMPLE_TIME;
    public static String URL;
    public static String LOGIN;
    public static String PASSWORD;
    public static List<String> HOSTS;

    public static void load(String file){

        Gson gson = new Gson();

        try {
            ConfigFile f = gson.fromJson(new FileReader(file), ConfigFile.class);
            System.out.println(f);
            ROOT = f.getLogFolder();
            SAMPLE_TIME = f.getSampleTime();
            URL = f.getVcenterUrl();
            LOGIN = f.getVcenterUserName();
            PASSWORD = f.getVcenterPassword();
            HOSTS = f.getHosts();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
